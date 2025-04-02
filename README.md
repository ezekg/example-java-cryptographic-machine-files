# Example Java Cryptographic Machine Files

This is an example of how to verify and decrypt [cryptographic machine files][docs] in Java 18 using Bouncy Castle.

This example verifies the `aes-256-gcm+ed25519` algorithm.

## Running the example

First, install dependencies with [`mvn`][maven]:

```
mvn compile
```

Then run the program:

```
mvn exec:java -Dexec.mainClass=sh.keygen.example.Main
```

You should see log output indicating the current machine file is valid:

```
Machine file signature is valid!
Machine file was successfully decrypted!
> Decrypted: {"data":{"id":"4cc1c12d-7063-468d-8eac-cd63d1dc1188","type":"machines","attributes":{"fingerprint":"c4:af:69:1a:ee:55:f1:b5:a4:09:fd:74:d3:1f:94:f0","cores":null,"ip":null,"hostname":null,"platform":null,"name":"Test","requireHeartbeat":false,"heartbeatStatus":"NOT_STARTED","heartbeatDuration":600,"maxProcesses":null,"lastCheckOut":null,"lastHeartbeat":null,"nextHeartbeat":null,"metadata":{},"created":"2025-04-02T12:38:09.018Z","updated":"2025-04-02T12:38:09.018Z"},"relationships":{"account":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52"},"data":{"type":"accounts","id":"1fddcec8-8dd3-4d8d-9b16-215cac0f9b52"}},"environment":{"links":{"related":null},"data":null},"product":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/machines/4cc1c12d-7063-468d-8eac-cd63d1dc1188/product"},"data":{"type":"products","id":"6db9ac6e-ea9e-4943-8462-a0315dda0f2e"}},"group":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/machines/4cc1c12d-7063-468d-8eac-cd63d1dc1188/group"},"data":null},"license":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/machines/4cc1c12d-7063-468d-8eac-cd63d1dc1188/license"},"data":{"type":"licenses","id":"a4797354-b53a-4641-b14d-89c4e87f9412"}},"owner":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/machines/4cc1c12d-7063-468d-8eac-cd63d1dc1188/owner"},"data":null},"components":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/machines/4cc1c12d-7063-468d-8eac-cd63d1dc1188/components"}},"processes":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/machines/4cc1c12d-7063-468d-8eac-cd63d1dc1188/processes"}}},"links":{"self":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/machines/4cc1c12d-7063-468d-8eac-cd63d1dc1188"}},"included":[{"id":"a4797354-b53a-4641-b14d-89c4e87f9412","type":"licenses","attributes":{"name":"Java Example","key":"33362C-D254BA-F54C3C-DAAE48-C71975-V3","expiry":null,"status":"INACTIVE","uses":0,"suspended":false,"scheme":null,"encrypted":false,"strict":false,"floating":false,"protected":true,"version":null,"maxMachines":1,"maxProcesses":null,"maxUsers":null,"maxCores":null,"maxUses":null,"requireHeartbeat":false,"requireCheckIn":false,"lastValidated":null,"lastCheckIn":null,"nextCheckIn":null,"lastCheckOut":null,"metadata":{},"created":"2022-04-01T13:17:43.813Z","updated":"2022-04-19T18:01:48.905Z"},"relationships":{"account":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52"},"data":{"type":"accounts","id":"1fddcec8-8dd3-4d8d-9b16-215cac0f9b52"}},"environment":{"links":{"related":null},"data":null},"product":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/product"},"data":{"type":"products","id":"6db9ac6e-ea9e-4943-8462-a0315dda0f2e"}},"policy":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/policy"},"data":{"type":"policies","id":"70bda6e4-2b9e-4100-946a-103164a2abc6"}},"group":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/group"},"data":null},"owner":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/owner"},"data":null},"users":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/users"},"meta":{"count":0}},"machines":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/machines"},"meta":{"cores":0,"count":1}},"tokens":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/tokens"}},"entitlements":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/entitlements"},"data":[]}},"links":{"self":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412"}}],"meta":{"issued":"2025-04-02T12:38:38.538Z","expiry":null,"ttl":null}}
```

## Questions?

Reach out at [support@keygen.sh][] if you have any
questions or concerns!

[docs]: https://keygen.sh/docs/api/cryptography/#cryptographic-lic
[maven]: https://mvnrepository.com/
[support]: mailto:support@keygen.sh
