# RideLoop — Bike Rental Management System

A Spring Boot + Thymeleaf + MySQL bike rental web application with customer and administrator roles. It includes a responsive, modern UI and a broad set of customer-retention, booking, support, fleet and reporting workflows.

## Technology
- Java 17+
- Spring Boot 3.5.5, Spring MVC, Spring Security, Spring Data JPA, Bean Validation
- Thymeleaf templates, responsive CSS, JavaScript
- MySQL 8+
- Maven

## Demo credentials
- Admin username: `admin`
- Admin password: `Admin@123`
- Customer: register at `/signup`

The administrator is a built-in demo account. Change/remove it and use a securely stored admin account before deployment.

## Run in Eclipse (Windows)
1. Install JDK 17 or newer and MySQL/HeidiSQL.
2. Extract this ZIP.
3. In HeidiSQL, run `CREATE DATABASE IF NOT EXISTS bike_rental_db;`.
4. Open `src/main/resources/application.properties` and set `spring.datasource.username` and `spring.datasource.password` to your local MySQL credentials.
5. In Eclipse choose **File → Import → Maven → Existing Maven Projects**, then select the project root folder containing `pom.xml` (not `src/main/resources`).
6. Wait for Maven dependencies to resolve.
7. Run `BikeRentalApplication.java` using **Run As → Java Application**, or run `mvn clean package` from the project root and then `mvn spring-boot:run`.
8. Open `http://localhost:8080`.

Hibernate creates/updates the application's tables automatically. A few demo bikes and starter coupons are seeded when the bike inventory is empty. If you already have the old project database, back it up before running this expanded version because the schema will be updated.

## Implemented application features

### Customer-facing
- Customer registration, login/logout, BCrypt password hashing, role-based access control
- Public landing page, responsive bike catalogue, bike detail pages, search and filters by type, price and pickup location
- Bike comparison table (select up to four bikes)
- Date/time-based reservations and overlap checks to prevent double booking
- Hourly/daily pricing, weekend price rule, long-rental discount, add-on equipment, coupon codes and loyalty point redemption
- Group booking for 2–5 bikes, booking modification and cancellation for upcoming bookings
- Customer dashboard, booking history, demo checkout, deposit-status tracking, rebooking and printable invoice (browser Print → Save as PDF)
- Saved/favourite bikes, waitlist entries and in-app availability notifications
- Price-drop alerts to users who saved a bike
- Reviews and ratings (reviews require a completed rental of that bike)
- Loyalty points, referral codes and demo referral bonuses
- Profile updates, limited document-verification request workflow (only document type and last four characters stored), and customer support tickets
- In-app notifications, trip cost estimator, rule-based rental assistant, rental plan information page

### Administrator
- Dashboard with fleet, availability, customer, booking and revenue metrics
- Bike inventory create/update/delete, photos by URL, location, rates, specifications and maintenance state
- Booking check-out and return workflow, late-return fee estimate, condition notes/photo URL, deposit review status
- Customer directory, document-verification review, maintenance/service records and return-to-ready workflow
- Coupon creation, expiry and activation/deactivation
- Support ticket replies and status tracking
- Reports for bookings, statuses, popular bikes, recorded rental revenue and maintenance costs; CSV export
- Scheduled in-app overdue reminders for bookings with a past return time

## Routes
- `/` — public homepage
- `/catalog` — bike catalogue and filters
- `/compare` — compare selected bikes
- `/signup`, `/login` — customer registration and sign-in
- `/customer/dashboard` — customer bookings and activity
- `/customer/trip-planner`, `/customer/assistant`, `/customer/reviews`, `/plans` — public planning/help pages or public review browsing
- `/admin` — admin dashboard
- `/admin/bikes`, `/admin/rentals`, `/admin/customers`, `/admin/maintenance`, `/admin/coupons`, `/admin/support`, `/admin/verification`, `/admin/reports` — admin tools

## Important integration notes
The project implements working demo flows, but some real-world services cannot be made live without business accounts, credentials, approved providers or hardware:
- **Payments:** checkout is explicitly a demo flag; connect Razorpay/Stripe or another provider and verify payment webhooks before taking money.
- **Email/SMS:** notifications are stored in the app; add SMTP/SMS provider credentials to send actual email/text messages.
- **AI assistant:** currently rule-based, not connected to an external AI model. Configure an API and guardrails to use a real model.
- **GPS/live maps:** the app links to map searches for pickup locations; live tracking requires suitable bike hardware and a mapping/GPS provider.
- **Identity verification:** only a limited metadata workflow is implemented. Use an approved identity provider and encrypted/private document storage before collecting real IDs.
- **Condition photos:** the return workflow accepts a photo URL; secure image upload/storage should be configured before production.
- **Loyalty/referral plans:** point and referral bonuses are demo business rules. Add anti-abuse rules, redemption terms and audited transactions before launch.
- **Student/corporate plans:** information/enquiry pathways are present; eligibility checks, negotiated pricing and invoicing need the business's actual rules.
- **Legal/production hardening:** review privacy, rental agreements, cancellation/deposit policy, tax receipts, data retention, CSRF/session settings, secrets management, database backups and security testing before public deployment.

## Database configuration
`src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bike_rental_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

Never commit a real production database password to source control.

## Live Cloud Deployment

### Option 1: Render (Free & Fast)
1. Push this repository to GitHub.
2. Sign in to [Render](https://render.com).
3. Click **New +** → **Web Service** → Connect your GitHub repository `jagadeeshwaran2006/springboot-web-application`.
4. Choose **Docker** runtime.
5. In **Environment Variables**:
   - Set `PORT` = `8080`
   - Set `SPRING_PROFILES_ACTIVE` = `h2` (for zero-setup instant demo) OR provide your MySQL credentials:
     - `SPRING_DATASOURCE_URL` = `jdbc:mysql://<your-db-host>:3306/<database>?useSSL=true`
     - `SPRING_DATASOURCE_USERNAME` = `<username>`
     - `SPRING_DATASOURCE_PASSWORD` = `<password>`
6. Click **Deploy Web Service**. Your live URL will be `https://<service-name>.onrender.com`.

### Option 2: Railway
1. Sign in to [Railway](https://railway.app).
2. Click **New Project** → **Deploy from GitHub repo**.
3. (Optional) Click **New** → **Database** → **Add MySQL**, then link `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, and `SPRING_DATASOURCE_PASSWORD`.
4. Railway will build using the `Dockerfile` and provide a public HTTPS domain.

