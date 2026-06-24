# elr-aphl-validation-plugins

Validation plugins for ELR (Electronic Laboratory Reporting) programs (ARLN, PHLIP, VPD, Rabies, and others). Each plugin enforces program-specific business rules against HL7 v2 messages.

---

## Configuration: `elr-plugins-config.properties`

Place `elr-plugins-config.properties` on the classpath (e.g. in `src/main/resources/` or alongside the JAR) before the application starts. The file configures two things per program:

1. **Data source type** — how validation data is loaded (`CSV` or `WS`)
2. **Data source location** — where that data comes from (file path, classpath resource, or URL)

### Data source type keys

Each plugin has one or more type keys that select the implementation used at runtime:

```properties
# Accepted values: CSV  (load from a CSV file)
#                  WS   (call a remote web service)
ARLN_OBX=CSV
ARLN_OBX3=WS
```

| Value | Description |
|-------|-------------|
| `CSV` | Reads validation data from local CSV files. Requires the corresponding `_CSV` path keys to be set. |
| `WS`  | Calls a remote APHL web service at runtime. Requires the corresponding `_WEBSERVICE_URL` keys to be set. |

---

### CSV mode

When a plugin's type key is set to `CSV`, it reads four CSV files: tests, observations, orders, and value sets. There are two ways to specify where those files live.

#### Option 1 — Absolute file-system path

Supply a full path to a file on disk. Useful during local development or when CSV files are managed outside the JAR.

```properties
ARLN_TEST_CSV=C:/data/arln/ARLN_Tests.csv
ARLN_OBSERVATIONS_CSV=C:/data/arln/ARLN_Observations.csv
ARLN_ORDERS_CSV=C:/data/arln/ARLN_Orders.csv
ARLN_VALUE_SETS_CSV=C:/data/arln/ARLN_ValueSets.csv
```

On Windows, either forward slashes (`C:/data/...`) or escaped backslashes (`C:\\data\\...`) are accepted.

#### Option 2 — Classpath resource (`classpath:` prefix)

Prefix the path with `classpath:` to load the file from the JAR or from any directory on the classpath. This is the recommended approach for bundled/production deployments because it avoids hard-coded machine-specific paths.

```properties
ARLN_TEST_CSV=classpath:arln/ARLN_Tests.csv
ARLN_OBSERVATIONS_CSV=classpath:arln/ARLN_Observations.csv
ARLN_ORDERS_CSV=classpath:arln/ARLN_Orders.csv
ARLN_VALUE_SETS_CSV=classpath:arln/ARLN_ValueSets.csv
```

The path after `classpath:` is resolved via the JVM classloader — relative to the root of the classpath (or the root of the JAR). Place the CSV files under `src/main/resources/` in the Maven project so they are included in the JAR automatically.

**Comparison**

| | File-system path | `classpath:` prefix |
|---|---|---|
| Good for | Local dev, externally-managed files | Bundled JARs, shared deployments |
| File location | Anywhere on disk | Inside JAR or on classpath |
| Path format | Absolute OS path | Relative to classpath root |
| Portability | Machine-specific | Portable |

---

### WS (web service) mode

When a plugin's type key is set to `WS`, validation data is fetched from a remote APHL service. Two URL keys are required:

```properties
ARLN_WEBSERVICE_URL=https://your-validation-service/arln
ARLN_VOCAB_WEBSERVICE_URL=https://your-vocabulary-service/arln
```

| Key | Purpose |
|-----|---------|
| `<PROGRAM>_WEBSERVICE_URL` | Main APHL validation service endpoint for the program |
| `<PROGRAM>_VOCAB_WEBSERVICE_URL` | Vocabulary/value-set service endpoint |

Web service responses are cached in memory for the lifetime of the process to avoid redundant network calls.

---

### Full example

```properties
# ── ARLN ─────────────────────────────────────────────────────────────────────
ARLN_OBX=CSV
ARLN_OBX3=CSV

# CSV files bundled inside the JAR
ARLN_TEST_CSV=classpath:arln/ARLN_Tests.csv
ARLN_OBSERVATIONS_CSV=classpath:arln/ARLN_Observations.csv
ARLN_ORDERS_CSV=classpath:arln/ARLN_Orders.csv
ARLN_VALUE_SETS_CSV=classpath:arln/ARLN_ValueSets.csv
ARLN_SPM4_CSV=classpath:foundation/SPM4_ValueSet.csv

# Web service endpoints (used when type key is WS)
ARLN_WEBSERVICE_URL=https://your-validation-service/arln
ARLN_VOCAB_WEBSERVICE_URL=https://your-vocabulary-service/arln

# ── PHLIP ─────────────────────────────────────────────────────────────────────
PHLIP_OBX=CSV
PHLIP_OBX3_OBR4_Warning=CSV
PHLIP_OBX3_OBR4=WS

# CSV files on the local file system (development)
PHLIP_TEST_CSV=C:/dev/phlip/PHLIP_Tests.csv
PHLIP_OBSERVATIONS_CSV=C:/dev/phlip/PHLIP_Observations.csv
PHLIP_ORDERS_CSV=C:/dev/phlip/PHLIP_Orders.csv
PHLIP_VALUE_SETS_CSV=C:/dev/phlip/PHLIP_ValueSets.csv

# Web service endpoints
PHLIP_WEBSERVICE_URL=https://your-validation-service/phlip
PHLIP_VOCAB_WEBSERVICE_URL=https://your-vocabulary-service/phlip
```

---

### CSV file format

All CSV files must have a header row. Column names are matched case-insensitively. If column names differ slightly from the expected names (e.g. `ARLN Value Set Name` instead of `Value Set Name`, or `Code System` instead of `CodeSystem`), the parser applies progressive normalization — stripping spaces, then suffix matching — before falling back to positional index. This means minor column name variations in externally-supplied CSVs are handled automatically.
