## Pre-requisite:
  - JDK 17
  - Selenium 4.xx
  - Test NG 7.xx
  - Maven 3.xx
  - JSON library

 ## Generate browser profile:
  - https://seleniumjava.com/2016/05/22/start-the-chrome-browser-with-extensions/
  - Place under `src/test/resources/browser-profile`

## Config:
  - Edit `browser.profile` in `config.properties` to it's appropriate directory
  - Toggle headless browser to true or false in `config.properties`
  - To add song and video titles, edit `src/test/resources/test-data/video-title.json`

## Execution:
  - `mvn clean test`