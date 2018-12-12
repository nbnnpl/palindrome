import socket, threading, pickle, sys, string


'''
Palindrome Server
Programmer: Nagendra nepal


class Server:

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    #First parameter means we're sending info via IPv4, second parameter means we have a TCP connection 

    connections = []
    #list of connections


    def __init__(self, ip = "127.0.0.1", port=8000):
    #server constructor
        self.sock.bind((ip, int(port)))
        #Binding our server to an IP and port
        self.sock.listen(2)
        #Listening for clients. (Up to 2 clients)
        print(f"Server has started. IP: {ip}, PORT: {port}")

    def handler(self, c, a):
        while True:
            data = c.recv(1024)
            data_variable = pickle.loads(data)
            #Max amount of data we can recieve is 1024 bytes
            for connection in self.connections:

                palindromeResult = self.palindrome(data_variable)
                palindromeResult.encode()

                connection.sendall(palindromeResult.encode())

            if not data_variable:
                self.connections.remove(c)
                c.close()
                break

    def palindrome(self, n):
        n = n.lower()

        for char in n:
            if char in string.punctuation or char == ' ':
                n = n.replace(char, '')

        print(n)

        if n == n[::-1]:
            return "This is a Palindrome!"
        else:
            return "This is not a Palindrome."

    def run(self):
        while True:
            c, a = self.sock.accept()
            #c = conneciton. a = address of the connection

            cThread = threading.Thread(target=self.handler, args=(c,a))
            cThread.daemon = True
            #This allows us to close the thread even if theres still a program running on it

            cThread.start()
            #Start the thread

            self.connections.append(c)
            #Add the connection to our list of connections 

            print(str(a[0]) + ' : ' + str(a[1]), "connected")


if(len(sys.argv) == 1):
    server = Server()
    server.run()
elif (len(sys.argv) > 1):
    server = Server(ip=sys.argv[1], port=sys.argv[2])
    server.run()
