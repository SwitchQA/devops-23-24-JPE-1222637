# CA1: Version Control with Git

Start Date: 26, February

End Date: 13, March

[Git Repository](https://github.com/SwitchQA/devops-23-24-JPE-1222637)

## Section 1: Analysis, design and implementation

### Part 1

#### Step 1: Copy the code of the Tutorial React.js and Spring Data REST Application into a new folder named CA1

- Create a repository in GitHub named ca1
- git clone that repository
- Copy the folder "Basic", and the pom.xml file from the root of the project located [here](https://github.com/spring-guides/tut-react-and-spring-data-rest)
- Paste on the root of the ca1 repository
- Create a .gitignore file 
- `git add .`
- `git commit -m "Initial commit"`
- `git push origin main`

#### Step 2: We should use tags to mark the versions of the application. You should use a pattern like: major.minor.revision (e.g., 1.1.0).
- `git tag v.1.1.0`
- `git push origin --tags` (this pushes all tags)

#### Step 3: Lets develop a new feature to add a new field to the application. In this case, lets add a new field to record the years of the employee in the company (e.g., jobYears).
- Create jobYears attribute in Employee.java
- Create getter and setter method. 
- Add jobYears to the following methods _equals_, _hashCode_ and _toString_.
- Add jobYears field to app.js
- Add value to DatabaseLoader.java 
- Add tests
- Debug the server and client parts of the solution.
  1. Run the server `- mvn spring-boot:run`
  2. Enter the following url in your browser: http://localhost:8080/
  3. Use React DevTools to inspect the state of the application.
  4. Inspect client server interactions on _Console_ tab.
  5. Inspect client code on _Sources_ tab
  6. To debug server side only simply apply a breakpoint and run a test on Debug mode.
- Commit the changes
  1. `git add .`
  2. `git commit -m "Add jobYears field to Employee"`
  3. `git tag -a v1.2.0 -m "Release version 1.2.0 with new feature"`
  4. `git push origin main `
  5. `git push origin v1.2.0`
- When Part 1 is completed tag with ca1-part1.
  - `git tag -a ca1-part1 -m "Part 1 completed"`
  - `git push origin ca1-part1 (this pushes this specific tag)`

##### Notes:

- On Step 1 it would also be valid to use git init locally and then create the repository in GitHub and use git mv to move the basic folder from the tutorial repository.
- Tests and validations should be done for the rest of the Employee fields, but it's beyond the scope of this assignment.

### Part 2

#### Step 1: Ensure you're starting on master branch

- `git checkout main`

#### Step 2: Create a new branch named "email-field"

- `git checkout -b email-field`
- `git push origin email-field` (this step was not done hence why Network Graph does not show the branch being pushed to the remote repository)

#### Step 3: Implement the new email field feature

- Same steps as Part 1 - Step 3
- Commit the changes and tag
  1. `git add .`
  2. `git commit -m "Add email field to Employee"`
  3. `git checkout main`
  4. `git merge email-field`
  5. `git tag -a v1.3.0 -m "Release version 1.3.0 with email field feature"`
  6. `git push origin main`
  7. `git push origin v1.3.0`

#### Step 4: Create a new branch for fixing bugs 

- `git checkout -b fix-invalid-email`
- `git push origin fix-invalid-email` (this step was not done hence why Network Graph does not show the branch being pushed to the remote repository)
- `git add .`
- `git commit -m "Fix email validation to ensure presence of '@'"`
- `git checkout main`
- `git merge fix-invalid-email`
- `git tag -a v1.3.1 -m "Release version 1.3.1 with email validation fix"`
- `git push origin main`
- `git push origin v1.3.1`
- When Part 2 is completed tag with ca1-part2.
  - `git tag -a ca1-part2 -m "Part 2 completed"`
  - `git push origin ca1-part2 (this pushes this specific tag)`

## Section 2: Analysis of an alternative

### Perforce Helix Core vs Git 

#### **1 - Perforce Helix Core**
- **1.1 - `p4 init`**: Initializes a new Perforce repository or workspace.
- **1.2 - `p4 add`**: Adds files to the depot for the first time, marking them for addition to version control.
- **1.3 - `p4 submit`**: Commits changes to the depot, including new files, changes to existing files, and deletions.
- **1.4 - `p4 push`**: In the context of Perforce, pushing typically refers to replicating changes to a remote server or depot, though Perforce's architecture uses a centralized model, so this operation might not apply as it does in distributed systems like Git.
- **1.5 - `p4 clone`**: While Perforce uses a centralized model, you can replicate or "clone" a repository setup by creating a new workspace with the same depot paths.
- **1.6 - `p4 labels`**: Manages labels (tags in Git) to mark specific points in the history for future reference.
- **1.7 - `p4 branch`**: Creates or manages branches within Perforce. Branches in Perforce are managed differently, often using "streams" for branch management.
- **1.8 - `p4 switch`**: Switches your workspace to a different branch or stream.
- **1.9 - `p4 merge`**: Merges changes from one branch into another. Perforce handles merging through a variety of commands depending on the specific workflow.
- **1.10 - `p4 debug`**: Provides tools for diagnosing and debugging Perforce server issues, not directly used for repository history or content like in distributed systems.

#### **2 - Breakdown of the Key Differences**
- **2.1 - Core Philosophies**
  - **Perforce**: Designed for enterprise-level scalability and management of large-scale projects, emphasizing robust access control and support for binary files.
  - **Git:** Focuses on distributed version control, enabling flexible workflows and local repository management.
- **2.2 - Branching and Merging**
  - **Perforce:** Uses a model that can be more structured, often leveraging streams for branching, which provides an explicit framework for managing branches.
  - **Git**: Offers lightweight branching, allowing for quick and easy creation and deletion of branches, fostering experimental development and complex workflows.
- **2.3 - Commands**
  - **Perforce**: Commands are designed to be explicit and integrate closely with the server-based repository model, focusing on centralized control and scalability.
  - **Git**: Commands cater to a wide range of version control tasks, supporting distributed workflows and local repository management.
- **2.4 - Performance**
  - **Perforce**: Optimized for handling very large codebases and binary files efficiently, making it ideal for certain types of large or complex projects.
  - **Git**: Highly efficient with textual content and smaller to medium-sized repositories but can struggle with large binary files without additional tools.
- **2.5 - Learning Curve**
  - **Perforce**: Can be steep due to its enterprise-level features and structured environment, especially for those new to version control.
  - **Git**: Also has a learning curve, especially to master its advanced features, but is widely adopted and supported by a vast array of learning resources.
- **2.6 - Community and Ecosystem**
  - **Perforce**: Strong in industries that deal with large codebases and assets, such as game development and enterprise software.
  - **Git**: Has a massive community across many sectors, supported by an extensive ecosystem of tools and integrations.

#### **3 - When to Consider Perforce Helix Core**
- **3.1 - Scalability and Performance**
  - Ideal for large, complex projects requiring efficient management of large files and binary assets.
- **3.2 - Centralized Control**
  - Projects that benefit from strict access control and centralized management may find Perforce's model more suitable.
- **3.3 - Industry-Specific Needs**
  - In industries like game development where handling large binary assets efficiently is crucial, Perforce offers significant advantages.

#### **4 - When to Consider Git**
- **4.1 - Distributed Development**
  - Git's distributed nature makes it ideal for projects with contributors working remotely and for open-source projects.
- **4.2 - Flexibility and Scalability**
  - Projects that value flexibility in workflow design and can manage with Git's scalability limits will benefit from its features.
- **4.3 - Community and Ecosystem**
  - The vast array of tools, integrations, and community support makes Git a compelling choice for a wide range of projects.

## Section 3: Implementation of an alternative

#### **1 - Setting up Perforce Helix Core**
1. **Install Perforce Server (P4D)**
  - Download P4D from the Perforce website [Perforce Downloads](https://www.perforce.com/downloads/helix-core-p4d). Follow the installation instructions provided by Perforce for Windows.

2. **Initialize Perforce Server**
  - After installation, start the Perforce server using the Perforce service or command line. This usually involves setting the P4PORT environment variable, e.g., `set P4PORT=1666`, and starting the server through the command line or services panel.

3. **Install Perforce Command-Line Client (P4)**
  - Download and install the Perforce Command-Line Client (P4) from the [Perforce Downloads](https://www.perforce.com/downloads/helix-command-line-client-p4) page. Follow the installation instructions for Windows.

4. **Set Up Perforce Environment**
  - Configure your environment:
    - Open a command prompt and set your server's port if necessary: `set P4PORT=1666`
    - Use `p4 login` to authenticate. You might need to create a user or use a default one provided by your Perforce server setup.

5. **Create a Workspace**
  - In the command prompt, type `p4 client myWorkspace`. This opens a configuration file in a text editor. Here, you'll need to set the `Root` to the directory where you want to store your files and adjust the `View` as necessary.

#### **2 - Basic Repository Operations**
1. **Navigate to Your Workspace Directory**
  - Use the `cd` command to navigate to your workspace directory, e.g., `cd path/to/myWorkspace`.

2. **Create a New File and Add it to Perforce**
  - Create a new file: `echo "Repository devOps - Luis Afonso" > README.md`.
  - Add it to Perforce: `p4 add README.md`.
  - Submit your change: `p4 submit -d "Initial commit"`.

3. **Ignore Files**
  - Perforce uses P4IGNORE for ignoring files. Create a `.p4ignore` file and list the patterns you wish to ignore.
  - Set the P4IGNORE variable: `set P4IGNORE=.p4ignore`.
  - Note: The direct equivalent of `.hgignore` functionality needs to be manually set in `.p4ignore`.

4. **Move Basic Folder and pom.xml to Your Repository**
  - Move your files into the workspace directory using Windows file explorer or the `move` command.
  - Add them to Perforce: `p4 add CA1\ basic\ pom.xml`.
  - Submit the changes: `p4 submit -d "Added CA1 dir and basic application"`.

#### **3 - Branching and Merging**
Perforce handles branching through "streams" or classic depot branching. This example uses classic branching.

1. **Create a Branch**
  - Branch by integrating files to a new depot path: `p4 integrate //depot/devOpsMain/... //depot/devOpsBranch/...`.
  - Submit the branch: `p4 submit -d "Branching off to devOpsBranch"`.

2. **Switch to the Branch**
  - Adjust your workspace view to point to the new branch, or manage branches more easily with streams.

3. **Merge Branch Back to Main**
  - Integrate changes back: `p4 integrate //depot/devOpsBranch/... //depot/devOpsMain/...`.
  - Resolve any conflicts.
  - Submit the merge: `p4 submit -d "Merged devOpsBranch into devOpsMain"`.

#### **4 - Handling Commits**
- Use `p4 edit` to open files for editing, then `p4 submit` to commit your changes after modifications.

#### **Conclusion**
This tutorial outlines the basic steps for setting up a local Perforce Helix Core environment on Windows, including initial commits, branching, and merging. Unlike distributed VCS like Git or Mercurial, Perforce's centralized model and file handling might require adapting your workflow.






