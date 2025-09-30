from flask import Flask, request, render_template
from flask_socketio import SocketIO, emit

app = Flask(__name__)
socketio = SocketIO(app)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/data', methods=['POST'])
def receive_data():
    data = request.json
    print("📩 받은 데이터:", data)
    # 웹페이지로 실시간 전송
    socketio.emit('new_data', data)
    return "OK"

if __name__ == '__main__':
    # 집 공유기 환경이면 host='0.0.0.0' 필수!
    socketio.run(app, host='0.0.0.0', port=5000)
