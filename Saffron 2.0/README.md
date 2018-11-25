# Saffron-2.0
Saffron is a preprocessor which serves as a front end for Sat4j. Saffron is to Sat4j as a higher level language is to an assembler. The user expresses his/her problem by writing a Java application using the Saffron API. When the application is run, Saffron creates a corresponding SAT problem, then applies the Sat4j API to find a solution. The Sat4j solution is analyzed by Saffron, and the bit assignments provided by Sat4j are used to construct the solution values of the original problem.

## Getting Started

1.	Download Saffron source files.
2.	Download the this (or a later) version of the following Sat4j library: org.sat4j.core_2.3.5.v201308161310.jar
3.	To familiarize yourself with the use of Saffron, run the numerous demo applications in the package named (of all things): demos. Extensive javadoc files can be found in the folder called: doc

## Running From The Windows Terminal

For instance, here's how the NaturalNumberXorerDemo app runs in my particular Java configuration:

cd C:\Users\Kerry\\.AndroidStudio2.2\system\restart\jre\bin\

java.exe -classpath "C:\Users\Kerry\git\Saffron-2.0\Saffron 2.0\bin;C:\Users\Kerry\Development\eclipse\plugins\org.sat4j.core_2.3.5.v201308161310.jar" demos.NaturalNumberXorerDemo

## Built With

* [Eclipse](https://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/photonr) - Java IDE
* [Sat4j](http://www.sat4j.org/) - Java SAT solver

## Author

* [**Kerry Michael Soileau**](http://www.kerrysoileau.com/index.html)

## License
This project is licensed under the GPLv3 License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

*	[Daniel Le Berre](https://github.com/danielleberre) provided invaluable guidance in connecting the Saffron code to the Sat4j API.
