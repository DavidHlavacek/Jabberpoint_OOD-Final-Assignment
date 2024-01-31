# A list of changes done to JabberPoint

| Nr. | Problem                                                                   | Solution                     | Argument                                              |
|-----|---------------------------------------------------------------------------|------------------------------|-------------------------------------------------------|
| 1   | Useless variables                                                         | Remove them                  | Makes code more maintainable and readable             |
| 2   | Constructors useless or used incorrectly                                  | Refactor and remove          | Code more readable and maintainable                   |
| 3   | Classes Presentation, SlideViewerFrame and SlideViewerComponent confusing | Refactor, Encapsulate better | Easier to debug, understand and makes it extendable   |
| 4   | Hierarchy                                                                 | Refactor                     | Poses problem when trying to extend or orient oneself |
| 5   | Code is not condensed                                                     | Apply functional programming | More readable and extendable                          |
| 6   | Bug where GO TO updates slide counter even if out of bounds               | Check bounds before setting  | Bug fixed                                             |
| 7   | Naming conventions                                                        | Fix naming conventions       | Better readability                                    |
| 8   | Typecasting                                                               | Typecast                     | Less error prone                                      |
