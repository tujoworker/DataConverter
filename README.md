# Maven Data Converter 🙀 API

## Info
This tiny app fetches data from two API sources and combines the data together to one API.

## Requirements
To build this app You need JDK and maven installed.
To run it, use e.g. Apache Tomcat server.

## Build

1. To build the app:
```bash
mkdir -p target && rm -rf target/* && mvn package
```

## Run

1. Copy first the /target/DataConverterWebApp/ folder and /target/DataConverterWebApp.war file into the Tomcat **/webapps** directory.

2. Start the server. The install directory may be different.
```bash
/Library/Tomcat/bin/startup.sh
```
3. And visit http://localhost:8080/DataConverterWebApp/

## Author
Tobias Høegh, tobias@tujo.no, *2017*
