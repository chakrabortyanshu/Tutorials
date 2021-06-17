### Mockito
Learning Mockito
Using 
https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/Mockito.html

Mockito Version 3.5.11

### Other Mockito Useful Links:
- https://javadoc.io/doc/org.mockito/mockito-junit-jupiter/latest/org/mockito/junit/jupiter/MockitoExtension.html
- https://github.com/mockito/mockito/wiki/What%27s-new-in-Mockito-2
- https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/hamcrest/MockitoHamcrest.html
- https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/ArgumentMatchers.html

### Useful Tips
#### Invalid use of argument matchers
  Mockito requires you to either use only raw values or only matchers when stubbing a method call. The full exception (not posted by you here) surely explains everything.
  Simple change the line:
> when(jdbcTemplate.queryForObject(anyString(), any(SqlParameterSource.class), String.class)).thenReturn("Test");

to

> when(jdbcTemplate.queryForObject(anyString(), any(SqlParameterSource.class), eq(String.class))).thenReturn("Test");

and it should work.
