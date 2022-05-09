# Rest Controllers, part II

Previously, we learned how to:

- Set up and use a RESTful Controller
- Make `GET` requests and return responses
- Specify `@PathVariables` to use data from the request path
- Designate a POJO as the JSON body of a response

***Now, let's look at finishing out the CRUD functionality of our API!***

We need to:

- Make `POST`, `PUT`, and `DELETE` requests
- Return meaningful responses for each of these *command* requests
  letting the client know if their request was successful (or not).

---

## 🚨 @PostMapping

Let's remember how a `POST` request differs from a `GET` request:

- In most `POST` requests, you have a **request body** which is used in the creation of a new record.
    - For example: a `Movie` JSON object we would see in our frontend Movies App.

- In `GET` requests, you do *not* have a request body.
    - Only query strings and path parameters can be defined

Much the same way we designated certain controller methods as accepting `GET` requests,
we do the same for methods we want mapped to a `POST` request:

```JAVA
@PostMapping
public void create(@RequestBody Movie newMovie){
    // movie things
}
```

Notice that this `create` method returns `void` and now ***accepts*** a `@RequestBody`.
That request body will be *deserialized* from JSON to an instance of a Movie object you defined previously!


!!! 👀

    If you haven't yet, go add a toString() override to your Movie class

Simply print out the movie object to verify you received the request successfully!
Check the ***console*** for the results.

!!! 🧠 `@RequestBody`

- Indicates a controller method's parameter should be designated as the request's body.
- It is important that the incoming request body properties match the intended target class' field names
    - If not, you could receive a deserialization exception!


***🐣 NOTE: You will not see the new movies actually saved because we don't yet have a persistence solution! 🐣***

---

## 🚨 createAll()

Following the above pattern, make a new controller method named `createAll`
which will accept as argument a ```List<Movie>``` instead of one `Movie`.

However, if you try to run this app, now we get a runtime exception!

```JAVA
java.lang.IllegalStateException: Ambiguous mapping.
Cannot map 'moviesController' method 
        com.codeup.fortran_movies_api.web.MoviesController#createAll(List)
to {POST [/api/movies]}: There is already 'moviesController' bean method
com.codeup.fortran_movies_api.web.MoviesController#create(Movie) mapped.
```

What does this mean??

Simple: Spring does not know which `POST` request to map to `/api/movies`

How to solve? Change the mapping of the new method to something else, like:

```JAVA
@PostMapping("all")
```

This will tell our application to listen on `/api/movies/all` for requests
and leave the previous `POST` method unchanged!

### 🧪 Testing
- Write an enhanced for-loop over the list of movies, printing each to the console and verify!

---

## Next Up: [Data Access](6-data-access-layer.md)