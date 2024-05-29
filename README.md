# MoviePlatform

The Java application is designed as a movie platform, providing functionalities to manage movies and user information effectively. At the heart of the application is the Main class, which serves as the entry point for the program. The main method within this class initializes the application, sets up necessary resources, and manages the overall flow of operations.

Central to the application are the Movie and User classes. The Movie class represents individual movies, encapsulating attributes such as the title, director, release date, genre, and rating. This class includes methods to manipulate and retrieve movie information, ensuring that movie data is handled efficiently and accurately.

Complementing the Movie class, the User class represents the users of the platform. It manages user-specific data, including username, password, email, and a list of movies the user has watched or rated. Methods within this class handle user authentication, profile management, and interaction with the movie database.

Supporting these core classes is the Utils class, which provides utility functions that streamline various operations across the application. This class includes methods for common tasks such as formatting dates, parsing strings, and handling file input/output operations. By centralizing these utilities, the application ensures code reusability and reduces redundancy.
