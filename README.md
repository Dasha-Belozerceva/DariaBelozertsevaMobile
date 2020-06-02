# DariaBelozertsevaMobile
Steps to run tests:
1. In Android studio create and lunch emulator: Pixel_3a_XL_API_29, check by adb if it's name is emulator-5554
2. Start Appium server
3. To run native application tests in terminal set command:
mvn -Pnative clean test
4. To run web application tests in terminal set command:
mvn -Pweb clean test
