before you start the app, you need to create collection in mongodb:
```
use rsvps;

db.createCollection( "meetupRSVP", { capped: true, size: 100000 } )
```