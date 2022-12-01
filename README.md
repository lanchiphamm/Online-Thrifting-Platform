# Online Thrifting Platform

## About 
### Purpose
**Online Thrift Store** is a platform where users can buy, sell, and browse secondhand clothes. 
Sellers can post their pre-owned goods with their price and description, while buyers can directly 
purchase or get connected to the owners of the pieces they are interested in for more information.

Online Thrift Store is targeted towards those who want to find new owners for the goods they are no 
longer interested in either through selling or donating, and those who want to purchase high-quality
unique pieces or try a new style while saving money. In addition, thrifting contributes
to helping the environment as it is a recycling process of labour and materials. It also helps 
preserve natural resources.

### Motivation
The idea stems from my own need I have outfits that I have worn only once or twice as they do not 
fit my style, and I would love to find new owners who can work these pieces in a fashionable way. 
Likewise, every so often I find it hard to buy low cost stylish or branded apparels unless I spend 
a lot of time browsing different sites or visiting physical stores. Although that is the fun of 
thrifting - putting time into finding the perfect vintage piece of clothing, I would at times opt 
for an easier and more time-saving route. The main idea behind this is to create a social media liked 
platform solely for the purpose of selling and buying used clothes where users can send message to 
each other as well


## User Stories
- As a user, I want to be able to add products I'm reselling to my profile
- As a user, I want to be able to view sellers and their products on the platform
- As a user, I want to be able to remove products I'm no longer selling from my profile
- As a user, I want to be able to add products I want to buy to my cart
- As a user, when I select the quit option from the application menu, I want to be reminded 
of the option to save my account or not
- As a user, I can either start fresh by signing up as a new user or log in to the account I've saved 
to the system from the last time and have all my information from that account loaded
- As a user, I want to be able to view products specific to my liking (later)
- As a user, I want to be able to view another person's profile and their products (later)
- As a user, I want to be able to buy an item or get connected to the seller of this item (later)
- add users to platform

## Instructions for Grader
To access the main GUI where you can generate the required events, you must login/register first.
Navigate to the User Profile tab, the required events are related to class *User* and class *Product*.
- 1st Event: Add multiple products (*Product*) to user profile (*User*)
    - On the **Add your Products** panel, specify the type, price, and description for you product
    - Click Add Product and the product you've just specified is added and display on your profile
- 2nd Event: Remove multiple products (*Product*) from user profile (*User*)
    - On the **Your Profile** panel, choose the product you want to remove from your profile by
  clicking on the list
    - Click Remove and the product you've just chosen is removed from your profile
**NOTE**: you can see these changes reflected by the number next to "Products: "
- You can locate the visual component by going to **User Profile tab**, on the **Add your Products**
panel, when you choose different clothing type (SHIRT, SHOES, etc.), different pictures are displayed.
- To save the state of the application to file, navigate to **Settings** tab, choose "Yes" then click
*Log out*. If you don't want to save the entire state, choose "No" and Logout.
- Loading the state of the application from file is performed when you log in with the account you 
have chosen to save before (when you Logout and chose "Yes"). Login into this account will reload all
the items you have added to your cart before and items on your profile.

## Phase 4: Task 2
- I'd clear the log every time a new user signs in, hence the "Event log cleared". Below is the 
event log of adding 4 products to your selling profile, then removing 2.

Thu Dec 01 15:04:59 PST 2022
Event log cleared.

Thu Dec 01 15:05:13 PST 2022
New SHIRT added to profile

Thu Dec 01 15:05:26 PST 2022
New PANTS added to profile

Thu Dec 01 15:05:36 PST 2022
New ACCESSORIES added to profile

Thu Dec 01 15:06:11 PST 2022
New OUTERWEAR added to profile

Thu Dec 01 15:06:18 PST 2022
Product (PANTS) removed from profile

Thu Dec 01 15:06:20 PST 2022
Product (ACCESSORIES) removed from profile

## References
- persistence package: Based on the supplied Workroom example for CPSC 210
  https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git 
- Event and EventLog class: Based on the Alarm System application from CPSC 210
  https://github.students.cs.ubc.ca/CPSC210/AlarmSystem 