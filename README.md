# ShortUrl

I write this simple Spring Boot Rest API which creates a short URL and then redirects to the original URL. The project consists of:
- Controller with all the operations of creation, reading, updating and deleting
- Service to calculate the short version of the URL
- Repository to save and read created URLs

For this example I used a map to store the data in order to make it simpler and easier to try on any environment but with a few modifications it is possible to store the data in all types of databases.