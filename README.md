# Communicator9000
Simple chat viewed with Swing, working with a simple server. Server logic uses derby db (jdbc) for user and password storage/verification (PKBDF2 algorithm). Client invokes server logic via RMI and uses the outcome.

Security issues are backed up by <b>PKBDF2 algorithm</b>, which is implemented based on jtan189's <a href="https://gist.github.com/jtan189/3804290">Java PKBDF2 Password Hashing Code</a>
