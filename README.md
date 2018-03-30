# Iflix code challenge

**Developer Name: Amila Iddamalgoda**

**Location: Colombo, Sri Lanka**

**Email: amila922@gmail.com**

**Contact: (+94) 716 360 132**

## Solution Overview
In order to address this challenge, I have used **java** as the primary programming language and **maven** as the project build tool. (Dependency management and etc.)

Java version: 1.8

## How to run the project
Go to the project directory. 
#### cd /iflix
#### mvn clean install exec:java
#### Results should be written to /data/result.json file
## or 
#### you can go to **/iflix/bin** and type **./run**
## or
#### java -jar iflix-challenge-1.0-SNAPSHOT-jar-with-dependencies.jar 
This jar is located in the iflix folder. This was created via mvn clean install package command.


# Problem

Welcome to the iflix Coding Assignment! We can't wait to see what you come up with!

**Please read through this entire file before starting the test.**

At iflix, we give our users free access through Partner networks - mobile or broadband, and we call these Offers. It's up to the partner how many months of free sweet sweet iflix each person gets. This assignment is focussed around solving a simplified version of that logic.

## Inputs

We've provided you two three different inputs into your solution. These are located within the `/data` path. You will need to use all of them:

* `accounts.json` - A list of our user accounts. You should ignore any data which doesn't map to an existing user account.
* `amazecom.json` - One of our Partners, AmazeCom provides us a list of Offers in JSON format.
* `wondertel.json` - Another one of our partners, WunderTel also provides us a list of Offers in the same JSON format.

The format and rules surrounding the Partner files are described below under **Offers**.

## Output

Your program will need to load these inputs, process them according to the rules under **Offers** below, and output a single file as `./output/result.json`, which contains the output from the analysis results.

The output should be focussed around each account. For each account, we want to know the number of whole days (rounded down) the user has had free iflix from each Partner.

The format of the JSON output file should match this structure:

```
{
  "subscriptions": {
    "Sally": {
      "amazecom": 34,
      "wondertel": 62
    },
    "Farhan": {
      "amazecom": 34
    }
  }
}
```

## Offer Rules

There are two inputs into the Offer algorithm:

* `GRANT` Offers, which give a user an Offer.
* `REVOKE` Offers, which removes the Offer.

Our Partners aren't aware of user accounts in our system, so instead they send MSISDNs for each user.

**GRANT Offers**

Format:

Each `GRANT` contains:

* The users phone number
* The date (ISO8601 format) at which the GRANT starts
* The period of free iflix for that user, in months.

Rules:

* The first partner to give a GRANT "owns" that user. Other partners cannot add to that users Offer.
* Expiring GRANTs from the same partner stack on top of each other if still active.
* Offer months are by calendar months, not in 30 day blocks.
* GRANT offers without a period defined should be ignored entirely.

**REVOKE Offers**

Each `REVOKE` contains:

* The users phone number
* The date (ISO8601 format) at which the REVOKE is effective from

Rules:

* A partner can only revoke an Offer if they "own" the user.
* REVOKE of an expired Offer is possible, the user should be "released" from the partner.
* REVOKEs should be processed before GRANTs.

**Examples**

```
Partner A issues a GRANT to John for 3 months.
Partner B issues a GRANT to John 4 months later.

John is still owned by Partner A, so Partner B's Offer is ignored
```

```
Partner A issues a GRANT to John for 3 months.
Partner A issues a REVOKE to John 2 months later.
Partner B issues a GRANT to John for 2 months at the exact same time as the REVOKE was issued.

John gets 2 months from Partner A and 2 months from Partner B.
```

```
Partner A issues a GRANT to John for 3 months.
Partner A issues a REVOKE to John 2 months later.
Partner B issues a GRANT to John for 2 months, but 1 day before the REVOKE was issued.

John gets 2 months from Partner A, and Partner B is ignored.
```

```
Partner A issues a GRANT to John for 4 months.
Partner A issues a GRANT to John for 6 months, 3 months later.

John has 10 months free iflix from Partner A.
```
