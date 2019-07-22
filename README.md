### 1. Running mvn project

    https://stackoverflow.com/questions/2472376/how-do-i-execute-a-program-using-maven  
    
    Running java application with maven:  
    mvn exec:java
    
### 2. SSL socket server  
    http://www.java2s.com/Tutorial/Java/0490__Security/SSLSimpleServer.htm
    https://www.youtube.com/watch?v=X4WImgwvjDw
    https://github.com/alexa/skill-samples-java/blob/master/address/pom.xml
    https://www.youtube.com/watch?v=QUrbNbxG2i0
    
    Generating keystore with date
    keytool -genkey -alias signFiles -keystore D:\OwnProject\keystore\mykeystore -validity 10000
    Show keystore info
    keytool -v -list -keystore <FileName>.keystore
    
### 3. Helpful java tips

```java 
public byte[] load() {
    File file = getFileFromResources();
    try (FileInputStream fileInputStream = new FileInputStream(file)) {
        return getBytesFromDataFile(file, fileInputStream);
    } catch (IOException e) {
        logger.error(e.getMessage());
    }
    return throwErrorMessage();
}
```
        We can replace method ```getBytesFromDataFile(file, fileInputStream);```
        with ```Files.readAllBytes(path)```. We have to remember that it could be not efficient with bigger files.
    
