from flask import Flask
from flask import request

import smtplib
from email.mime.text import MIMEText

app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])

def parse_request():
    data = request.data.split("|")
    sendEmail(data[0], data[1])
    return "Cool!"


def sendEmail(title, body):
    fromaddr = 'auto.orayta.books@gmail.com'
    toaddrs  = 'orayta.books@gmail.com'

    msg = MIMEText(body)

    msg['From'] = fromaddr
    msg['To'] = toaddrs
    msg['Subject'] = "Subject: Orayta Error Report - " + title

    # Credentials (if needed)
    username = 'orayta.books'
    password = 'na,rahT9'

    # The actual mail send
    server = smtplib.SMTP('smtp.gmail.com:587')
    server.starttls()
    server.login(username,password)
    server.sendmail(fromaddr, toaddrs, msg.as_string())
    server.quit()