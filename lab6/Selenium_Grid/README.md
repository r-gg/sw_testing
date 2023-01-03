# Selenium Grid

## How to start the grid and run the tests

### Starting the grid

#### Running on host (one) machine in standalone mode

Change directory to `lab6/Selenium_Grid`

Execute:
```shell
java -jar selenium-server-4.7.2.jar standalone --max-sessions 3
```
We limit the maximal number of concurrent sessions to 3 for performance reasons.

#### Running multiple Docker containers 

Change directory to `lab6/Selenium_Grid`

Execute:
```shell
docker compose up
```
### Running the tests

Run Cucumber tests as in the previous labs. (either with Maven/Gradle or directly in IntelliJ)

## Adaptations Made

1. We firstly added the `SeleniumRemoteWebDriver` class, which is almost identical to the already implemented `SeleniumWebDriver` class, except that it uses a remote host (the **Hub**) to execute the tests.

2. `SeleniumWebDriver` class has then been exchanged with the new class in `BugstoreStepDefinitions` class.

3. Since the parallel execution of cucumber tests is by default disabled. It had to be enabled with `@ConfigurationParameter` annotation.

4. In the new class `SeleniumRemoteWebDriver`, the `RemoteWebDriver` object was wrapped with `ThreadLocal` so that each Scenario has its own WebDriver. (Due to extremely sparse documentation and forum support, this required the greatest effort to solve.)

**Selenium Grid UI is available on http://localhost:4444/ui.**

Note: When testing on different browser with docker, the chrome nodes have to be exchanged with the corresponding browser type.

### Further useful commands

Start a hub on this machine:
```shell
java -jar selenium-server-4.7.2.jar hub
```

Register this machine as a node (After starting the hub):
```shell
java -jar selenium-server-4.7.2.jar node --port 5556 --hub http://localhost:4444 --max-sessions 2
```

### Resources
_Helpful resource for creating the docker containers_: https://github.com/SeleniumHQ/docker-selenium
