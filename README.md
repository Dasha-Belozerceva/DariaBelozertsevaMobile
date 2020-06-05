# DariaBelozertsevaMobile
Steps to run tests:
<ol>
<li>1. In Android studio create and lunch emulator: Pixel_3a_XL_API_29, check by adb if it's name is emulator-5554</li>
<li>2. Start Appium server</li>
<li>3. To run native application tests in terminal set command:
mvn -Pnative clean test</li>
<li>4. To run web application tests in terminal set command:
mvn -Pweb clean test</li>
<li>5. To run native application tests on cloud in terminal set command:
  <ol>
    <li>for android:
mvn -PandroidCloudNative clean test</li>
<li>for iOS:
mvn -PiosCloudNative clean test</li>
  </ol>
    </li>
  <li>
6. To run web application tests on cloud in terminal set command:
    <ol>
    <li>for android:
      mvn -PandroidCloudWeb clean test</li>
<li>for iOS:
  mvn -PiOSCloudWeb clean test</li>
    </ol>
  </li>
  </ol>
