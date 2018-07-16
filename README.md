Implemented APIs
==================

Profile
------
- GET /api/profile - get profile 
- POST /api/profile - create profile
- PATCH /api/profile - update profile data
- GET /api/profile/{id} - get public information about other user's profile 

League
------
- GET /api/leagues - get leagues using parameters (((pagination AND page) XOR name) AND mine)
- POST /api/leagues - create league (user which creates league will be automatically added to the league)
- GET /api/leagues/{id} - get league details 
- GET /api/leagues/{id}/games - get games ids for given league
- PUT /api/leagues/{id}/games/{gameId} - adds game to given league
- PUT /api/leagues/{id}/games - adds games to given league
- GET /api/leagues/{id}/bets - get all bets ids for given league. Additional query 
parameter (mine) is permitted, when equals to true then only user's bets are returned

Response body
```json
[
  {"id": 1}, 
  {"id": 2}
]
```
- DELETE /api/leagues/{id}/games/{gameId} - removes game from given league
- PUT /api/leagues/{id}/users - adds user to given league
- DELETE /api/leagues/{id}/users - removes user from league

Game
----
- GET /api/games/{id} - get game details
- GET /api/games/{id}/bets - get bets ids for given game

Bet
---
- GET /api/bets - get all user's bet ids
- GET /api/bets/{id} - get user's bet details
- POST /api/bets - create new bet

Request body
```json
{
  "gameId": 1,
  "leagueId": 1,
  "homeScore": 0,
  "awayScore": 0
}
```
- DELETE /api/bets{id} - remove bet

Model
=====
DataIntegrityViolationException
-------------------------------
If request is going to break any of the database constraints, 
as response 409 http will be returned with given json response:
```json
{
  "message":"Request has caused invalid state of data in DB",
  "httpCode":409,
  "errorCode":"INVALID_REQUEST"
}
```
In the future, more useful information will be provided.