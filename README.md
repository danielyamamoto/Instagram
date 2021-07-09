# Project - *Instagram Clone*

**Instagram** shows the latest images currently post of our Instagram clone. The app utilizes Parse to get and post information.

Submitted by: **Daniel Yamamoto**

Time spent: **20** hours spent in total

## User Stories

The following **required** functionality is completed:

* [ ] User can **sign up** to create a new account using Parse authentication
* [x] User can **log in** of his or her account
* [x] The current signed in user is persisted across app restarts
* [ ] User can log out of his or her account
* [x] User can take a photo and add a caption
* [x] User can post it to "Instagram"
* [x] User can view the last 20 posts submitted to "Instagram"
* [x] User can pull to refresh the last 20 posts submitted to "Instagram"
* [x] User should be displayed the relative timestamp
* [ ] User can tap a post to view post details

The following **stretch** features are implemented:

* [ ] Style the login page to look like the real Instagram login page
* [ ] Style the feed to look like the real Instagram feed
* [ ] User can load more posts once he or she reaches the bottom of the feed using endless scrolling
* [x] User should switch between different tabs using using fragments and a Bottom Navigation View
  * [x] Feed Tab (to view all posts from all users)
  * [x] Capture Tab (to make a new post using the Camera and Photo Gallery)
  * [x] Profile Tab (to view only the current user's posts, in a grid)
* [ ] Show the username and creation time for each post
* [ ] User Profiles
  * [ ] Allow the logged in user to add a profile photo
  * [x] Display the profile photo with each post
  * [ ] Tapping on a post's username or profile photo goes to that user's profile page and shows a grid view of the user's posts
* [ ] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse
* [ ] User can comment on a post and see all comments for each post in the post details screen
* [ ] User can like 👍 a post, and see number of likes for each post in the post details screen

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='walkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [Kap](https://getkap.co/).

## Notes

Describe any challenges encountered while building the app.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright 2021 Daniel Yamamoto

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.