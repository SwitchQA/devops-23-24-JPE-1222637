# CA1: Version Control with Git

Start Date: 26, February

End Date: 11, March

### Section 1: Analysis, design and implementation

#### Step 1: Copy the code of the Tutorial React.js and Spring Data REST Application into a new folder named CA1
 
- Copy the folder "Basic", and the pom.xml file from the root of the project located in https://github.com/spring-guides/tut-react-and-spring-data-rest

#### Step 2: We should use tags to mark the versions of the application. You should use a pattern like: major.minor.revision (e.g., 1.1.0).
- git tag v.1.1.0
- git push origin --tags

#### Step 3: Lets develop a new feature to add a new field to the application. In this case, lets add a new field to record the years of the employee in the company (e.g., jobYears).
- Create jobYears attribute in Employee.java
- Create getter and setter method. 
- Add jobYears to the following methods _equals_, _hashCode_ and _toString_.
- Add jobYears field to app.js
- Add value to DatabaseLoader.java 
- Add tests
- Debug the server and client parts of the solution.
  1. Run the server - mvn spring-boot:run
  2. Use React DevTools to inspect the state of the application.
  3. Inspect client server interactions on _Console_ tab.
  4. Inspect client code on _Sources_ tab
- Commit the changes
  1. git add .
  2. git commit -m "#5 - Add jobYears field to Employee"
  3. git tag -a v1.2.0 -m "#5 Release version 1.2.0 with new feature"
  4. git push origin main 
  5. git push origin v1.2.0


