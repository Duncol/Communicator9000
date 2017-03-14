# Communicator9000
Simple chat viewed with Swing, working with a simple server. Server logic uses derby db (jdbc) for user and password storage/verification (PKBDF2 algorithm). Client invokes server logic via RMI and uses the outcome.

Security issues are backed up by <b>PKBDF2 algorithm</b>. It's implementation is based on jtan189's <a href="https://gist.github.com/jtan189/3804290">Java PKBDF2 Password Hashing Code</a>

Login client itself contains "login" field, "password" field, "login" button and "create new user" label. Both login and password field have implemented FocusListener which provides hint of the desired content.

<b>RUNTIME</b>
Client verifies user's credentials (login and opassword) and can create new user in server-side db via RMI. Only after this verification chat window is made, which connects to server side with a standard Java Socket/ServerSocket. Afterwards, new thread awaiting for new messages is initialized and a similar one - that sends new messages to all clients - starts running on the server side.
Client's chat window sends messages with a simple ActionListener when "Send" button is pressed. Message - along with sender's ures name - goes to the server, where current time is appended. After that, server sends the message to all current users (user list is a static field with global access. That provides an up-to-date state when server-side sends a new message.
