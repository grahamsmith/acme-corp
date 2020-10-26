# Acme Corp

## Notes

### Initial thoughts

- Assuming given there is a refresh token this is some sort of oauth system.
- The flows are incomplete so no direction given on what happens on the next warm & cold restart. Will assume for now that until the API says unauthorised to keep the user logged in. On 401 try to re use the refresh token to get another access token and on failure pop the user back to the login screen.
- Need to check the API’s headers for the list response to see if it caches it. We should try to honour this as the client.
- Was vague on the responses for the API, looked like everything *could* return a 200 OK with meta data, so tried them in chrome to confirm behaviour (this is cheap and quick), did this multiple times to establish the behaviour is consistent. Works as expected where api status codes are in fact returned.
- Logo is not great for app icon, probably won’t have time to fix this properly however spending some 1:1 time with the designer would be a good way to address this.
- Logo is a PNG!
- User messages are not internationalised. Would be good to send a language accepts header, which can be multiple in priority order, based upon what the user selected in the Operating System. This would allow for messages to be returned either in the right language or with a reference to the in built strings file. Former is more flexible.
- Given the amount of time probably need to use the in built design elements of Material design.
- Hate the register button, it is not a “real” button has a small touch area and also makes registration a 2nd class citizen. The App can be “smart” in the future about what to show and how to show it.
- The placeholder text colour will not meet WCAG 2.0 standards as the contrast is not high enough. Speak to designer as we can easily fix this.
- Need to ask API friends to version the API if it is not already. Future proofing.


### Architecture + design

The app makes no use of any Android specific features, while this may change in the future right now I would want a team chat about the right solution for this problem. e.g. Flutter etc. However this is an Android job so I better write some Android code.

Given the simple nature of the App, a single activity solution with two fragments would be sufficient. We can potentially benefit from the advantages of a SAA with regards to lifecycle.

Admittedly fragments are a little contentious but they are a standard component, which would make onboarding a new dev easier. Using the support versions will provide the least risk here.

We need DI to create well structured code. Familiar with Dagger. Worked on Hilt with Google during testing. Did you know it was going to be called Cloak? Myself and others told the feedback session members from Google how bad a product called “Cloak and Dagger” would sound in the context of Google. Could also use Koin (service locator) etc.

Use MVVM. Lets pull our business logic away from Android to make it easier to test. Other MV* frameworks are available.

Since we have auth and networking, making use of Retrofit/OkHTTP’s interceptors maybe useful.

Security wise there is not a lot going on here but we can implement some things like cert pinning on the production builds to help mitigate MITM attacks. Storing the access and refresh tokens in the Android Account Manager per OWASPs recommendations. Using the Account Manager is also great for user experience as a user can find all their accounts in one place. Not sure in a few hours there is time to do this.

Would say use vector graphics where possible but there is only the logo and I don’t have the original file to create an SVG. Notes do day don’t worry about pixel perfect so we can move on. Will use in built XML gradients from the design. -makes notes to discuss better handover system with designer than using screenshots in Google Drive-.

Dev grumble - building scrollable lists is long winded annoyance using RecyclerViews. Can't wait for Jetpack Compose to mature.

"Top Rated" - Would be better to have something more specific on the API perhaps, as this could be a sellable feature to be in effect have a “promoted” card.  (this is debatable and all hypothetical). Will need to check if the API always returns in the assumed order or top rated first or does the client need to re order the items?  Makes testing a little harder as to not know the full expectation.

Ignoring the hamburger menu - no use for it at the moment. Could be converted to Kebab to offer logout using standard toolbar components. Probably not enough time to do this. Unsure how logout should work? Do users even logout by choice? Do they just uninstall?

Designs are only portrait. Will be locking the app to portrait. If given more time we could look at supporting bigger screens and landscape.

### Testing

- Do not have too long but will try to show an assortment of tests, which can be later added to.
- Will use JUNIT to do unit testing.
- Will use OkHttp’s mock web server to help testing. Means we can test without the main server via integration tests.
- Using DI means we can mock the Android dependencies and test away from Android on the much faster local JVM.
- Not testing dependencies (OkHTTP/Retrofit/etc) or Android as these are well tested entities.
- Given time unlikely to get chance to do UI tests but we can look at using Espresso or even the test recorder if it generates immediate results.
- Need to give thoughts to the Android app lifecycle, mainly how networking behaves. Do network requests get cancelled when pausing and resuming? Probably not in this scenario so we will need to handle this.
- Won’t have time / ability given the scope but probably look at:
	Ongoing app size.
	Regression testing.
	SAST via CI pipeline (we will want CI at some point, “works on my machine” avoidance).
	Analytics for user research and crashlytics for real time issue detection.
	Proguard / R8


### Notes while building implementation

- No requirements for Android version provided, set to Android 6.0 to cover 85% of devices. Logic that spanning 5 major versions of Android should be enough. Can always see the value in supporting older versions later.

- Set for latest stable SDK as the target - 29.

- Add some dependencies for networking and serialisation/demoralisation (RETROFIT and OKHTTP)

- Chose Moshi as it has a tiny method count next to GSON and takes normally around half the time to deserialise a payload (performance++). Jackson is amazing especially when used with Spring (Jackson is default here so we might get some consistency benefits), however it has a HUGE method count, which would lead to multi-sexing and a larger app size.

- API Does not have consistent base URL will need to work with API guys to see if this is a mistake etc.

- API is not versioned. App also should probably send its version up via interceptor but alas time.

- Using same view holder for both top rated and “normal” views - could be 2 but not enough in it.

- Design is inconsistent the “Contact” button is not all caps

- Logo is useless for the toolbar, we need a better logo to make this work.

- Miles away should be a double or similar from the API as currently string manipulation is hard. Have left broken intentionally as alternative is to strip the “m” from the string and parse but this is a hack not a fix. If also a number then we can sort by it. We can also be much smarter and use string quantities to say 1 mile or 2 miles etc.

- Ratings header “top rated”  is ambiguous and not documented. Can be added via another view holder in the adapter. Is it highest number of reviews AND highest rank? Docs say order by number of reviews but this seems odd UX without further detail.

