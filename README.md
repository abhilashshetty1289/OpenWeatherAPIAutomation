# automation
HighLow Automation System

1. git clone the project.
2. Open IDE and import the project as Maven project.
3. After importing the project, open terminal and run 
  a. mvn dependency:resolve
  b. mvn compile
4. Download the required browser libraries binaries depending on the platform from http://www.seleniumhq.org/download/
5. Save the binaries for example chromedriver.exe, etc under the drivers folder.
6. In the project.properties file under src/main/resources. In the file specify for chrome.driver.location
eg: chrome.driver.location=${project.basedir}/drivers/chromedriver
7. Do the same for different browser libraries.
8. Run the test for a particular maven profile using the command
-> mvn test -P<name-of-the-profile>
eg: for CRM testing
-> mvn test -PCRMTesting
