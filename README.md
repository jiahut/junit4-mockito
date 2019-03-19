## run 

> gradlew run


## dependencyReport

> gradle dependencyReport


## monitor for gradle test

```bash 
/usr/local/bin/fswatch -l 1  **/*.java  build.gradle  | xargs -I _ gradle test
```
