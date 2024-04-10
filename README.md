# Innowise: CryptoCurrency Telegram Bot

## Tags: 
- #telegram_bot

## Task
It is necessary to develop a Telegram bot to track the rates of cryptocurrencies. When the bot is registered, it will have to send messages to the user when one of the currencies goes up/down by N percent. To check the exchange rate this link should be used: https://api.mexc.com/api/v3/ticker/price

## Required technologies
1. **Java / Spring Boot**
2. **Postgresql**
3. **No Spring Data JPA**

## Required functionality
Application must:
1. **Have any user-friendly interface**
2. **Application and rates should be updated at least every 20 seconds**
3. **User should be able to select at which currency rate change he/she will receive changes (e.g. 3% / 5% / 10% / 15%)**
4. **Changes of exchange rates are fluffed by a separate message each time**
5. **The user should be able to view currency rates at a given time (e.g. by clicking on the "Current rate" button in the bot interface).**
6. **Add traffic limiter by number of users. Telegram bot can support X users at most. When X+1th user connects to the bot, he/she should be notified that the bot is unavailable.**

## Additional functionality
- **User should be able to follow cryptocurrecny pair changes and get notifications only about followed notifications**

##Constraints
1. **This assignment is designed to take 4-5 hours to complete**
