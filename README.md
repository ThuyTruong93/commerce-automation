
# User Guide

# Introduction

This repository contains all automated tests for Katalon platform product and owned by Quality Engineering team

- Automation Tool: Katalon Studio (Platform Or Edition)
- Language: Groovy, Java
- Design Pattern: Fluent Page Object Model
- Support: API and UI application
- SUT/AUT: Katalon TestOps (Web Application, Rest APIs)

# **Prerequisites**

- Java 8+ installed already
- Katalon Studio installed already (latest version)

# **High-Level Design**

![APPROACH](Images/Readme/automation_approach.png)

# **Usage**

## **UI Automation - Framework Structure**


### <ins> **Profile** </ins>
**Objectives**: Consists of test environment configuration/ global variables for running tests
![PROFILE](Images/Readme/profile.png)

### <ins> **Keyword** </ins>
![KEYWORD](Images/Readme/keyword_fw.png)
- `katalon.fw.*`: ***Using the keywords for building core framework function***
  - `BaseElement.groovy`: Define wrap-up methods of findTestObject from Object Repository to locate element on Page Object Classes (eg. class, id, link, name, css ...)
    ![BASE ELEMENT](Images/Readme/base_ele.png)
  #####
  - `Page.groovy`: Generate Page Object Classes leverage singleton design pattern in 2 forms (Lazy - *creation of instance at load time* & Early Instantiation - *creation of instance when required*) to help to save memory because object is not created at each request
    ![PAGE](Images/Readme/page.png)
  #####
  - `BasePage.groovy`: Parent page for other page objects classes, contains common functions on many pages
    ![BASE PAGE](Images/Readme/base_page.png)
  #####
  - `KEventHandler.groovy`: Custom driver event handler listener to listen driver event log during running test
    ![K EVENT](Images/Readme/k_event.png)

  #####
- `katalon.*<feature>.groovy`: ***Implement the Page Object Model (POM Pattern) to make the Test Objects (UI Objects) attached to their pages (Page Objects) to do the Web actions such as clicking a button or filling in a textbox***
![KEYWORD](Images/Readme/keyword_page.png)
  - Those are organized based on the feature site maps and each class will be inherited from `BasePage` class
  - Must not contain utility functions, it should be implement as web element-action-based design
  ![POM](Images/Readme/page_design.png)

### <ins> **Object Repository** </ins>

![OBJECT REPOSITORY](Images/Readme/object_repository.png)
- **Objective**: To avoid object duplication during development time and help to reduce performance issue, as well as simply the way to define test objects
- **Recommendation**: The Scripter should try to use dynamic element to avoid creating too many test objects.
- **Usages**:  
  * btn: To locate Button element, only need input label of button which want to locate to
![BUTTON](Images/Readme/btn.png)
  * class: To locate element by class attribute, only need input class name of element which want to locate to
![CLASS](Images/Readme/class.png)
  * name: To locate element by name attribute, only need input name attribute value of element which want to locate to                                                                            
![NAME](Images/Readme/name.png)                                       
  * id: To locate element by id attribute, only need input id of element which want to locate to   
![Id](Images/Readme/id.png)
  * link: To locate a link element with tag name 'a', only need input link text of element which want to locate to   
![LINK](Images/Readme/link.png)
  * text: To locate element by text, only need input text value of element which want to locate to   
![TEXT](Images/Readme/text.png)
  * txt: To locate checkbox element by checkbox label, only need input checkbox label value of element which want to locate to   
![TXT](Images/Readme/txt.png)
  * title: To locate element by title attribute, only need input title value of element which want to locate to   
![TITLE](Images/Readme/title.png)
  * css: To locate element by css                                                                                         
![CSS](Images/Readme/css.png)

### <ins> **Test Cases** </ins>
![TEST CASE](Images/Readme/test_case.png)
- Test scripts are organized based on the site maps. For examples, test cases for Application Repository feature should be stored under Testops > Test Execution > Application Repository folder.
- **Usages**: 
  - Required follow naming convention as [automation practices](https://katalon.atlassian.net/wiki/spaces/ENG/pages/2354249760/WIP+Automation+Practices)
  - Test case design follow Fluent Page Object Model
  ![TEST CASE EG](Images/Readme/test_case_eg.png)

### <ins> **Test Data | Data Files** </ins>
- **Objectives**: Contains all of data-driven test for automated tests, leveraging excel file to store data, and organized follow site map of AUT
- **Usages**:
  - Each WorkBook represent for a feature (eg. TestManagement, Configurations, etc.)
  - Each WorkSheet represent for a test script, sheet name is required the same name as test case id (eg. AC-FA38401)
- Screenshot: TBD

### <ins> **Test Listener** </ins>
![TEST LISTENER](Images/Readme/test_listener.png)
- Handle test hook actions(@BeforeTestCase, @AfterTestCase, @BeforeTestSuite, @AfterTestSuite...)
### <ins> **Test Suites: TBD** </ins>

## **API Automation** 
### <ins> **Structure** </ins>
- Leverage UI automation structure, applying Fluent Page Object Model and organizing structure as site maps of UAT
- **BaseService.groovy**: 
  - Objectives: contains common functions for API endpoint testing (eg. set baseUrl, set Auth, setPayload, setMethod, ect.), it will be inhered from services pages
![BASE SERVICE ](Images/Readme/base_service.png)
- `katalon.service.*.groovy`: 
  - Objectives: contains all api service pages classes, those are also organized based on the feature site maps
  - Usages: Design test follow fluent pattern, **remember to `.initRequestObject()` to initialize request instance**
![PAGE SERVICE ](Images/Readme/service_page_eg.png)
- **Testcases**: The same UI, test cases are organized follow feature site maps or UAT
  - Usages: 
    - Test case design follow Fluent Page Object Model and test case convention as UI test case mention
    - Leverage **Page** class to generate Page Service class 
![API TESTCASE ](Images/Readme/api_test_case.png)
# Report: *(TBD)*
# Reference Sources
- [Automation practices](https://katalon.atlassian.net/wiki/spaces/ENG/pages/2354249760/WIP+Automation+Practices)

