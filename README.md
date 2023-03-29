# Shopping Cart Application

This is a simple shopping cart application required by the interview

There are some assumptions made for this task:
- Reading input (from command line, file, etc) is not important to this task. Therefore they're passed in the Main function hardcoded
- Using database is not required, therefore an in-memory list is used for the inventory
- Item names are unique and can be used as identifier
- Item names are case in-sensitive
- `Double`  type can be used for simple prices and calculations, no need for a more advanced type for simplicity

## How to run tests
`sbt clean test`

## How to run program
Run main function in the Main class from IDE

## How to give input
Update item list in the Main class