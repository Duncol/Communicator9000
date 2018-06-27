# Communicator9000
<b>PROGRAM - AS WELL AS THIS README FILE - IS STILL UNDER CONSTRUCTION</b>

<b>TO-DO LIST:</b>
 - refactoring
 - debugging
 - elimination of some "hard coded" settings like ip, ports etc. (toSpring())
 - implementation of few additional features (more complex layout in GUI, other rooms, maybe file attachment option)
 
 <hr>

Simple chat viewed with Swing, working with a simple server. Server logic uses derby db (jdbc) for user and password storage/verification. Client invokes server logic via RMI and uses the outcome. Project was created and developed with Maven.

Security issues are backed up by <b>PBKDF2 algorithm</b>. It's implementation is based on jtan189's <a href="https://gist.github.com/jtan189/3804290">Java PKBDF2 Password Hashing Code</a>

Login window itself contains "login" field, "password" field, "login" button and "create new user" label. Both login and password field have implemented FocusListener which provides hint of the desired content.

Create user window on the other hand consists of one "login" field to define new user login, and two password fields to define password for newly created user. The second one is to assure, that there is no mistake in typing and that the password is not a random input.


 <b>HOW TO RUN IT</b>
 
This program is managed and build with Maven. It uses Maven Shade Plugin, to pack program itself and all it's dependencies into one uber-jar. To run it you need to download the projects and in Eclipse: Run As -> Maven build... (goal "package") or just type in terminal in project's directory:
<blockquote>mvn compile<br>
mvn package</blockquote> 
It will produce a jar file inside "target" directory. To run it, open project containing dir and type in terminal: 
<blockquote>java [name-of-jar]</blockquote>
By now, for test purposes, it is configured to run on "localhost". First you need to run "MyServer" in order to manage requests of future clients. If server is established correctly, you should see few messages:
- "Server socket established" (port binding phase)
- "Registry created!" (RMI)
- "Derby SQL connection successfuly established" (initiates jdbc driver, and tries to connect to given db; if it doesn't exist it creates new one
- "Table with a given name already exists. Let me use it" (In this case given table inside db was already created, so it simply uses it. If there is no such table with a given name it creates a new one)
- "PassCheckService established!" (RMI, used for login/password verification with db)
- "CreateUserService established!" (RMI, used to create new user in db)
- "IsLoggedService established!" (RMI, checks if given user (login) is loged already. We do not allow clones)
- "The gates has been opened..." (method socket.accept() starts and awaits for new clients)

<b>RUNTIME</b>

Client login window verifies user's credentials (login and password) and can create new user in server-side db - both are managed by RMI. Only after the verification - chat window is made - which connects to server side with a standard Java Socket/ServerSocket. Afterwards, new thread awaiting for new messages is initialized (BufferedReader) and a similar one - that sends new messages from that client to all clients - starts running on the server side.
Client's chat window sends messages with a simple ActionListener when "Send" button is pressed. Message - along with sender's user name - goes to the server, where current time is appended. After that, server sends the message to all current users (user list is a static field ArrayList of PrintWriters with global access. That provides an up-to-date state when server-side sends a new message). When message is delivered via reader it is immediately appended to JTextArea.

<b>SERVER CODE (PSEUDO) UML</b>

<img src="https://github.com/Duncol/Communicator9000/blob/master/MyServer-UML.png">
