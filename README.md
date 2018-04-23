A simple example of async tasks which happen in every application. 

**Objectives:** Request list of repositories for a user from Github API. Asynchronously request the last commit for each repo. Add a Data Persistency Layer. Add unit tests.

#### What is implemented:

1. `View-ViewModel-Repository` as an architecture pattern (without data binding). View includes Android classes, the rest can be tested with JUnit. 

1. `RepoWithCommitListViewModel` loads list of repos first, then asks each repo for the last commit and notifies View when it's done. 

1. Network (Retrofit) and DB (Room) requests in Repository run with Kotlin coroutines

1. Unit tests for Repository.


#### What is not implemented:

1. Config change handling. 
There are different ways to tackle this problem, e.g. store ViewModels in retained fragment, 
then reattach to new View or implement full Rx solution etc.

1. Some things are implemented "well enough", but require tuning before going live in production. 
E.g. `notifyDataSetChanged()` is called on adapters every time a single row changes.

1. Proper DI