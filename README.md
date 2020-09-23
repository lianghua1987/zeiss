
## API Documentation

Currently, there is only a websocket connection which allows receiving a soft-realtime event stream of machine status updates. The endpoint’s full url is: ws://machinestream.herokuapp.com/ws, the response looks like this:

```json
{
  "topic": "events",
  "ref": null,
  "payload": {
    "machine_id": "59d9f4b4-018f-43d8-92d0-c51de7d987e5",
    "id": "41bb0908-15ba-4039-8c4f-8b7b99260eb2",
    "timestamp": "2017-04-16T19:42:26.542614Z",
    "status": "running"
  },
  "event": "new"
}
```

The status can be either idle, running, finished or errorred in which case they will be repaired automatically and a repaired event will be sent before resetting to idle again.

The socket will timeout after 60 seconds.


To test: http://localhost:8080/api, only shows all machines current status
