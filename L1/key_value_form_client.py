import grequests
import sys


if __name__ == '__main__':
    url = 'http://localhost:8080/laborator1/KeyValueServlet'

    params = {
        'key' : 'key',
        'value' : 1,
        'mock' : 'is-mock',
        'sync' : 'is-sync'
    }

    request = grequests.post(url = url, params = params)
    response = request.send()
    
    print (response.response.content.decode('utf-8'))