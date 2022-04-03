# Stealth-API---UC-Machine-Coding

You’ve just been hired by a new stealth startup which wants to introduce new ways of storing data. You’ve been tasked with building v1 of this product. The management team knows that they will be on the front page of techcrunch, reddit, hacker news and every other major news site on the first day they launch so they have asked you to ensure that your design is as performant on a single box as possible.
						
Here are the three HTTP requests that you need to implement 

GET /add?value=20&jobid=1
returns a proper 200 HTTP response with a body of {“stat:”ok} (json encoded dictionary)
		
GET /all?startValue=8
Returns a table that shows all the jobs in sorted order of value.
If startValue is present, it starts from values greater than or equal to startValue
						
POST /remove?jobid=1				
Returns immediately, and also removes the job with jobid=X. The response to this request should be {“stat”:”ok”} (unless the request wasn’t found in which case you should decide what the most appropriate response is.)
						
The last catch is that your VP of Engineering doesn't like external databases (in fact, that's the reason you are building one), and have asked you to not use any of them. 						
In conclusion the 3 major requirements are
Implement the above APIs
As efficiently as possible (be prepared to discuss techniques and your choices)
WIthout using an database
