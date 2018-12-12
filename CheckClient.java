import socket, threading, sys, pickle

'''
Palindrome Client
Programmer: Nagendra nepal
'''

class Client:

    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def sendMsg(self):
        while True:
            data_string = pickle.dumps(input(""))
            self.sock.send(data_string)

    def __init__(self, address="127.0.0.1", port=8000):
        self.sock.connect((address, int(port)))

        iThread = threading.Thread(target=self.sendMsg)
        iThread.daemon = True
        iThread.start()

        while True:
            data = self.sock.recv(1024)
            if not data: 
                break
            print(str(data.decode("utf-8")))


if(len(sys.argv) == 1):
    client = Client()
elif (len(sys.argv) > 1):
    client = Client(address=sys.argv[1], port=sys.argv[2])
