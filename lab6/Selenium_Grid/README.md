# Selenium Grid

## How to start

### Running on host (one) machine in standalone mode

```shell
java -jar selenium-server-4.7.2.jar standalone --max-sessions 3
```
We limit the maximal number of concurrent sessions to 4.

### Running multiple containers 

```shell
docker compose up
```

Run Cucumber tests as in the previous labs.

## Adaptations Made

We firstly added the `SeleniumRemoteWebDriver` class, which is almost identical to the already implemented `SeleniumWebDriver` class, except that it uses a remote host (the **Hub**) to execute the tests.

`SeleniumWebDriver` class has then been exchanged with the new class in `BugstoreStepDefinitions` class.

Since the parallel execution of cucumber tests is by default disabled. It had to be enabled with `@ConfigurationParameter` annotation.

UI Available on http://localhost:4444/ui.

