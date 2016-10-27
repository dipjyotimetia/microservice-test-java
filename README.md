# microservice-test-java
mvn clean install -Dbrowser=chrome -Denv=local -Dcommon_selenide_timeout_ms=7000 -Dcucumber.options="--tags @all";mvn site
mvn clean install -Dselenide.browser=chrome -Dcucumber.options="--tags @order" -Dremote=http://localhost:4444/wd/hub -Dselenide.browser-size=400x600;mvn site
