##  startup

> gradle build


## dependencyReport

> gradle dependencyReport


## monitor for gradle test

> brew install fswatch

```bash 
/usr/local/bin/fswatch -l 1  **/*.java  build.gradle  | xargs -I _ gradle test
```

https://javacodehouse.com/blog/mockito-tutorial/
