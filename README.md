# sesha-assignment
Spring boot application which uses JPA and h2 database to do CRUD operations. 

The application is dockerized and deployed in opensource cloud platform render.com. A CI/CD platform is created and with each commit to the repository, a job is executed which creates a docker image and deployes it on render.com

Endpoints available:

To read userdetails by ID:
https://sesha-assignment.onrender.com/users/getUserById/1001

********************************************

To register a new user:

https://sesha-assignment.onrender.com/users/register

with body :

{
    "name": "jack",
    "username": "jack",
    "password": "mysecretpassword"
}

********************************************

To login:

https://sesha-assignment.onrender.com/users/login

with params :

username : jack
password : mysecretpassword

********************************************

To read userdetails by name : 
https://sesha-assignment.onrender.com/users/getUserByName/John

********************************************

To upload an image : 
https://sesha-assignment.onrender.com/images/uploadImage

with body :
{
    "file": {yourimage}.jpg,
    "id": {userid}//should be available with previous endpoint
}

The image is uploaded using imgur and the link for image will available while reading the uploaded image.


********************************************

To read image details using image id:

https://sesha-assignment.onrender.com/images/getImageByImageId/{imageid}

Example :
https://sesha-assignment.onrender.com/images/getImageByImageId/1

********************************************

To read image details using user id: 

https://sesha-assignment.onrender.com/images/getImagesByUserId/{userid}

Example :
https://sesha-assignment.onrender.com/images/getImagesByUserId/1

********************************************

To delete images by user id :

https://sesha-assignment.onrender.com/images/deleteImagesByUserId/{userid}

Example :
https://sesha-assignment.onrender.com/images/deleteImagesByUserId/1
