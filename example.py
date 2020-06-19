from datetime import datetime
a=[]
import psycopg2
try:
    connection = psycopg2.connect(user="postgres",
                                  password="cdgouri",
                                  host="127.0.0.1",
                                  port="5432",
                                  database="grocery")
    cursor = connection.cursor()
    


    postgreSQL_select_Query = "select * from analytics where analytics.timestamp > extract(epoch from (now() - interval '15 days'))"

    cursor.execute(postgreSQL_select_Query)
    #print("Selecting rows from analytics table using cursor.fetchall")
    product_analytics = cursor.fetchall() 
    a=product_analytics
    # print(len(a))
   
    #print("Print each row and it's columns values")
    #print(product_analytics)
except (Exception, psycopg2.Error) as error :
    print ("Error while fetching data from PostgreSQL", error)

finally:
    #closing database connection.
    if(connection):
        cursor.close()
        connection.close()
       # print("PostgreSQL connection is closed") 
productlist=[]


from heapq import nlargest
class Product:
    def __init__(self,serialnumber,name,timestamp,sales):
        self.serialnumber =serialnumber
        self.name = name
        self.timestamp = timestamp
        self.sales = sales
    def getName(self):
        return self.name
    def getSales(self):
        return self.sales
def getTopSelling(productlist):
    return nlargest(3,productlist,key=lambda product:product.getSales())
for i in range(len(a)):
    productlist.append(Product(a[i][0],a[i][1],a[i][2],a[i][3]))
tuple(productlist)
top = getTopSelling(productlist)
print('These are top 3 best selling products:')
for i in top:
    print(i.getName(),':',i.getSales(),', ')
    
