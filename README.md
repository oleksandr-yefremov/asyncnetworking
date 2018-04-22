What is not implemented:

1. Config change handling. 
There are different ways to tackle this problem, e.g. store ViewModels in retained fragment, 
then reattach to new View or implement full Rx solution etc.

2. Many things are implemented "well enough", but require tuning before going live in production. 
E.g. `notifyDataSetChange()` is called on adapters every time a single row changes.

Unit tests for Repository as a bonus.