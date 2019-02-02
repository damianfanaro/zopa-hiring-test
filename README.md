GET /lender
    -> Returns a list of lenders data.
    
GET /lender/{id}
    -> Returns the details of the lender with ID {id}.

POST /lender
    -> Adds a new lender into the system.
    -> Example payload in JSON format:
        {"name": "Bob", "interest": 0.04, "available": 5600}
    
GET /loan?requested={pounds}&timePeriod={months}&allOrNone=1
    -> Returns an estimation of the loan interest (if possible) along with
       the monthly repayment and total repayment information.
       
       requestedLoan: the quantity of money of the loan (it must be within the valid range).
       timePeriod:    requested time in months for the repayment.
       allOrNone:     true or 1 if the requested loan must be fulfilled completely or rejected otherwise,
                      false or 0 if the requested loan could be fulfilled partially.
