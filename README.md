# Data Converter 🙀

## Info
This tiny app fetches data from two API sources and combines the data together to one single outputfile: **API.json**

## Requirements
To run this app You need Java installed. To easily run it, use Terminal or other bash command tools. It's not required to have JSON package installed, as this follows along with this app.

## Usage

1. To build the app, in one line:
```bash
mkdir -p build | rm -rf build/* | find . -name "*.java" > .sources | javac -d "build" -classpath "build" @.sources
```

2. Then run the app:
```bash
cd build
java DataConverter
```


## More details on how to build and run

1. Make sure you are in the source folder of this app.
2. If the app is already build, skip step 3 and 4

3. To build this app, run:
```bash
find . -name "*.java" > .sources
javac -d "build" -classpath "build" @.sources
```

4. Run the app by:
```bash
java -classpath "build"  DataConverter
```

5. You will get the content of the API call as a JSON file, in a file called API.json

6. To build a executable jar file, run from the build folder, this command, hence You have the added the required manifest.mf file with at least this inside "Main-class: DataConverter":
```bash
jar -cmf manifest.mf DataConverter.jar
```

## Author
Tobias Høegh, tobias@tujo.no, *2017*
