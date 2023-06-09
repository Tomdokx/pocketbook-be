<h1>Pocketbook Backend Application</h1>

<p>Welcome to the Pocketbook Backend Application! This project serves as the backend for the Pocketbook application, providing essential functionalities for managing user data, notes, and tasks. Below, you'll find a brief overview of each section in the README.</p>

<h2>Settings</h2>

<p>The Pocketbook Backend Application is configured with the following settings:</p>

<ul>
  <li><strong>Database:</strong> The application utilizes a PostgreSQL database running on port 5432. The database name is "pocketbookPostgres". This database stores the necessary data for user management, notes, and tasks.</li>
  <li><strong>Application Port:</strong> The Pocketbook Backend Application runs on port 9000. This is the port where the application listens for incoming HTTP requests.</li>
  <li><strong>Context Path:</strong> The application has a context path of "/api/v1". This is the base URL path that precedes all the API endpoints of the application.</li>
</ul>

<p>These settings ensure proper connectivity to the PostgreSQL database and provide the necessary configuration for accessing the application's resources through the specified port and context path. All of those can be changed with file <code>application.properties</code></p>

<h2>Database Entity-relationship model</h2>
<p>In this section, you'll find information about the database ERP model used in the Pocketbook Backend Application. It outlines the structure and relationships of the database tables, ensuring efficient storage and retrieval of data.</p>

<img src="https://github.com/Tomdokx/pocketbook-be/blob/master/pocketbookerd.jpg" alt="pocketbook ER model">

<h2>Controllers</h2>

<p>This section introduces the various controllers used in the Pocketbook Backend Application. These controllers handle the logic and functionality related to user management, notes, and tasks. The controllers included are:</p>

<ul>
  <h3>UserController</h3>
<ul>
  <li><strong>GET /users</strong>: Retrieves a list of all users.</li>
  <li><strong>GET /users/{id}</strong>: Retrieves a single user based on the provided ID.</li>
  <li><strong>POST /users</strong>: Creates a new user.</li>
  <li><strong>PUT /users/{id}</strong>: Updates specific information of a user based on the provided ID.</li>
</ul>

<h3>NoteController</h3>
<ul>
  <li><strong>GET /notes</strong>: Retrieves a list of all notes.</li>
  <li><strong>GET /notes/{id}</strong>: Retrieves a single note based on the provided ID.</li>
  <li><strong>POST /notes</strong>: Creates a new note.</li>
  <li><strong>PUT /notes/{id}</strong>: Updates specific information of a note based on the provided ID.</li>
</ul>

<h3>TaskController</h3>
<ul>
  <li><strong>GET /tasks</strong>: Retrieves a list of all tasks.</li>
  <li><strong>GET /tasks/{id}</strong>: Retrieves a single task based on the provided ID.</li>
  <li><strong>POST /tasks</strong>: Creates a new task.</li>
  <li><strong>PUT /tasks/{id}</strong>: Updates specific information of a task based on the provided ID.</li>
</ul>
</ul>

<p>These endpoints allow you to perform various operations, such as retrieving all objects, retrieving a specific object, creating new objects, and updating existing objects. You can integrate these endpoints into your frontend application to interact with the Pocketbook Backend Application effectively.</p>

<p>There is more than that, also there are enpoints for changing password, make task done / undone etc.</p>

<h2>Security</h2>

<p>The Pocketbook Backend Application implements robust security measures using Spring Security and JWT (JSON Web Tokens) for authentication and authorization.</p>

<p>JWT tokens are utilized to securely authenticate and authorize user requests. Each token has an expiration time and is issued upon successful login. The token should be included in the "Authorization" header of subsequent API requests using the format: <code>bearer &lt;token&gt;</code>.</p>

<p>Spring Security provides a comprehensive set of features to manage authentication and access control. It offers various authentication mechanisms, including password-based authentication, OAuth, and more.</p>

<p>With the combination of Spring Security and JWT tokens, the Pocketbook Backend Application ensures that only authenticated and authorized users can access the protected resources, maintaining a secure environment for user data.</p>

<h2>Testing and Error Handling</h2>

<p>This section outlines the approach to testing and error handling in the Pocketbook Backend Application. It discusses techniques used for unit testing, integration testing, and error handling strategies to maintain application stability and reliability.</p>

<h2>Project Structure</h2>

<p>The Pocketbook Backend Application follows a structured organization with the following folders:</p>

<h4>Config</h4>
<p>The <strong>Config</strong> folder contains custom configurations for date and other application settings. It provides a centralized location to manage and customize the application's behavior.</p>

<h4>Controllers</h4>
<p>The <strong>Controllers</strong> folder contains the user, task, and note controllers. These controllers handle the incoming HTTP requests, process the logic, and return the appropriate responses. They serve as the entry point for the REST API endpoints.</p>

<h4>DataClasses</h4>
<p>The <strong>DataClasses</strong> folder contains the classes responsible for modeling the data entities in the application, such as the User, Task, and Note classes. These classes define the structure and behavior of the data objects used throughout the application.</p>

<h4>Repositories</h4>
<p>The <strong>Repositories</strong> folder contains the DAOs (Data Access Objects) for each class. These repositories provide an abstraction layer to interact with the database and perform CRUD (Create, Read, Update, Delete) operations on the corresponding data entities.</p>

<h4>Security</h4>
<p>The <strong>Security</strong> folder houses all the classes related to JWT security implementation. It includes classes responsible for token generation, validation, and authentication. The security classes ensure the protection of sensitive data and provide secure access to the application's resources.</p>

<h4>Services</h4>
<p>The <strong>Services</strong> folder contains classes specifically dedicated to handling login-related functionalities. These services handle the authentication process, user login, and related operations to manage user sessions and security aspects.</p>

<p>The organized project structure enhances maintainability, readability, and modularity of the Pocketbook Backend Application, making it easier to navigate, understand, and extend the codebase.</p>

<p>Feel free to explore each section in detail to gain a comprehensive understanding of the Pocketbook Backend Application. If you have any questions or require further assistance, please don't hesitate to reach out. Happy coding!</p>
