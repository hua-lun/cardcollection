# Card Collection REST Service with Spring

### Reference Guide
[Building REST services with Spring](https://spring.io/guides/tutorials/rest#header)

## Card Collection API
A service to manage Pokémon TCG Cards collection

HTTP requests using curl

- Add new cards into collection
- Get All cards in collection
- Get information on a Single card in collection
- Update quantity of a Single card
- Delete card in collection

### Example POST request
Supports old and new format of processing information

**[Old] With setName**
```bash
curl -v -X POST localhost:8080/cards -H 'Content-Type:application/json' -d '{"pokemonName": "Piplup", "setName": "Sun & Moon, Ultra Prism", "cardNumber": "31/138"}' | json_pp
```