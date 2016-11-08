Usage:
-------
mvn clean install -Dcucumber.options="--tags @catalog";mvn site

mvn clean install -Dcucumber.options="--tags @order";mvn site

mvn clean install -Dcucumber.options="--tags @all" -Dselenide.browser=chrome;mvn site

mvn clean install -Dcucumber.options="--tags @order" -Dselenide.browser=chrome -Dremote=http://localhost:4444/wd/hub

mvn clean install -Dcucumber.options="--tags @order" -Dselenide.browser=chrome -Dremote=http://localhost:4444/wd/hub -Denv=remote

mvn clean install -Dcucumber.options="--tags @order" -Dselenide.browser=chrome -Dremote=http://localhost:4444/wd/hub -Denv=remote -Dselenide.browser-size=800x600

mvn clean install -Dbrowser=microservice.browser.ChromeAppleIphone5DriverProvider -Dcucumber.options="--tags @order"

mvn clean install -Dbrowser=microservice.browser.ChromeAppleIphone5RemoteDriverProvider -Dcucumber.options="--tags @order" -Denv=remote
