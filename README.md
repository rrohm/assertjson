# AssertJSON
AssertJson is a library for testing json data, i.e., for examining JSON content, it's structure and it's attributes.

It also aims at beeing a simple, no-dependency, tool for fetching JSON content from REST resources.

Example:
``` java
HTTPJsonClient.GET("http://localhost:" + JAX_WS_PORT + "/openejb/categories")
        .authenticate("Superuser", "super")
        .send()
        .assertNotNull()
        .assertStatus(200)
        .assertJSON()
        .isArray()
        .assertNotEmtpy();
```

