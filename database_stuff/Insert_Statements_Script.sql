-- Use the database
USE TaskManagementDB;

-- Insert 5 Teams
INSERT INTO Team (TeamName)
VALUES 
('Photojam'),
('Buzzster'),
('Kwimbee'),
('Flipstorm'),
('Tagcat');

-- Insert 10 Users
INSERT INTO User (Username, Email, Password, Role, TeamID)
VALUES 
('jmyall0', 'cjaggli0@si.edu', 'gS7$@p,zgd', 'ADMIN', 1),
('obony1', 'gbrobeck1@illinois.edu', 'cV9''S{L2)|W(xy', 'EMPLOYEE', 1),
('awalshaw2', 'rdigwood2@t.co', 'oE0%&t}\P0GI', 'ADMIN', 2),
('jaberchirder3', 'ccorzon3@ca.gov', 'jU6<s(T%', 'EMPLOYEE', 2),
('hlasslett4', 'hoshields4@wikispaces.com', 'vI8$oZ>0tq/', 'ADMIN', 3),
('shaukey5', 'nmacken5@google.com.hk', 'hD6?59!tI(', 'EMPLOYEE', 3),
('rmcnulty6', 'gbunson6@geocities.com', 'eG1.\'\'Dn', 'ADMIN', 4),
('rogready7', 'kgothrup7@apple.com', 'eU0(+QU2', 'EMPLOYEE', 4),
('aeastment8', 'gmatitiaho8@google.es', 'hD5"5@8n', 'ADMIN', 5),
('tscranny9', 'gcurlis9@theglobeandmail.com', 'pM8~!H&QPL(H21', 'EMPLOYEE', 5);

-- Insert 10 Projects
INSERT INTO Project (ProjectName, Description, StartDate, EndDate, TeamID)
VALUES
('Project Infinity', 'A cutting-edge software development project aimed at revolutionising how businesses manage their operations and interact with customers.', '2024-01-01', '2024-03-15', 1),
('Tech Solutions', 'A comprehensive project focused on providing innovative technological solutions to address various business challenges and improve efficiency.', '2024-05-05', '2024-07-20', 1),
('Marketing Makeover', 'An exciting project dedicated to revitalizing the company\'s marketing strategies, enhancing brand awareness, and attracting new customers.', '2024-03-10', '2024-05-25', 2),
('Operation Efficiency', 'A strategic initiative aimed at streamlining internal processes, reducing costs, and improving overall operational efficiency.', '2024-04-15', '2024-06-30', 2),
('Data Integration', 'A project focused on integrating disparate data sources to provide a unified view of information and enable better decision-making.', '2024-05-20', '2024-07-15', 3),
('Customer Experience Enhancement', 'An initiative aimed at enhancing customer satisfaction by improving every touchpoint of the customer journey.', '2024-06-25', '2024-08-10', 3),
('Product Launch', 'A project focused on successfully launching a new product into the market, from conception to post-launch marketing and support.', '2024-07-30', '2024-09-15', 4),
('Strategic Planning Initiative', 'A project dedicated to developing and implementing long-term strategic plans to guide the company\'s growth and success.', '2024-09-05', '2024-10-20', 4),
('Mobile App Development', 'An exciting project focused on designing and developing a mobile application to meet the needs of modern users.', '2024-10-10', '2024-12-25', 5),
('Website Redesign', 'A project aimed at redesigning the company\'s website to improve user experience, increase traffic, and drive conversions.', '2024-11-15', '2024-12-31', 5);

-- Insert 30 Tasks
INSERT INTO Task (TaskName, Description, Status, Priority, StartDate, CompletionDate, UserID, ProjectID)
VALUES
-- Tasks for Project Infinity
('Research and Analysis', 'Research and analyze existing software systems for potential areas of improvement.', 'COMPLETED', 'MEDIUM', '2024-01-01', '2024-01-15', 2, 1),
('Prototype Development', 'Develop prototype features for the new software solution based on user feedback.', 'COMPLETED', 'HIGH', '2024-01-16', '2024-03-14', 1, 1),
('Usability Testing', 'Conduct usability testing to ensure the software meets user requirements and expectations.', 'COMPLETED', 'MEDIUM', '2024-02-25', '2024-03-14', 2, 1),

-- Tasks for Tech Solutions
('Technological Challenges Assessment', 'Identify key technological challenges within the organisation and prioritise them based on impact.', 'COMPLETED', 'HIGH', '2024-05-05', '2024-05-15', 2, 2),
('Software Solution Implementation', 'Design and implement custom software solutions to automate repetitive tasks and streamline workflows.', 'IN-PROGRESS', 'HIGH', '2024-05-16', NULL, 2, 2),
('Employee Training on New Technologies', 'Provide training sessions to employees on using new technologies effectively to maximise productivity.', 'IN-PROGRESS', 'MEDIUM', '2024-05-05', NULL, 1, 2),

-- Tasks for Marketing Makeover
('Market Research and Analysis', 'Conduct market research to understand consumer preferences and competitor strategies.', 'COMPLETED', 'HIGH', '2024-03-10', '2024-04-10', 4, 3),
('Marketing Plan Development', 'Develop a comprehensive marketing plan outlining strategies for brand repositioning and customer engagement.', 'COMPLETED', 'HIGH', '2024-04-11', '2024-05-10', 4, 3),
('Branding Implementation', 'Implement new branding elements across various marketing channels, including social media, email campaigns, and advertising.', 'COMPLETED', 'MEDIUM', '2024-05-11', '2024-05-25', 4, 3),

-- Tasks for Operation Efficiency
('Operational Process Analysis', 'Perform a thorough analysis of current operational processes to identify bottlenecks and inefficiencies.', 'COMPLETED', 'HIGH', '2024-04-15', '2024-05-15', 3, 4),
('Lean Management Implementation', 'Implement lean management principles to streamline workflows and reduce waste.', 'COMPLETED', 'HIGH', '2024-05-16', '2024-06-15', 3, 4),
('Performance Monitoring and Improvement', 'Monitor key performance indicators (KPIs) to track improvements in operational efficiency over time.', 'IN-PROGRESS', 'MEDIUM', '2024-06-16', NULL, 3, 4),

-- Tasks for Data Integration
('Data Source Identification and Scope Definition', 'Identify data sources and determine the scope of data integration requirements.', 'COMPLETED', 'HIGH', '2024-05-20', '2024-06-05', 6, 5),
('Data Mapping and Transformation', 'Develop data mapping and transformation processes to ensure compatibility and consistency across datasets.', 'IN-PROGRESS', 'HIGH', '2024-06-06', NULL, 6, 5),
('Data Validation Implementation', 'Implement data validation procedures to verify the accuracy and completeness of integrated data.', 'COMPLETED', 'MEDIUM', '2024-07-06', '2024-07-15', 5, 5),

-- Tasks for Customer Experience Enhancement
('Customer Feedback Analysis', 'Conduct customer surveys and feedback analysis to identify pain points and areas for improvement.', 'NOT-STARTED', 'HIGH', NULL, NULL, 6, 6),
('Personalised Communication Strategies', 'Implement personalised customer communication strategies to enhance engagement and satisfaction.', 'COMPLETED', 'HIGH', '2024-07-11', '2024-07-30', 5, 6),
('Customer Touchpoint Optimization', 'Regularly review and optimise customer touchpoints, including website navigation, support interactions, and product/service delivery.', 'IN-PROGRESS', 'MEDIUM', '2024-07-31', NULL, 5, 6),

-- Tasks for Product Launch
('Product Specifications Definition', 'Define product specifications and features based on market research and customer feedback.', 'COMPLETED', 'HIGH', '2024-07-30', '2024-08-10', 7, 7),
('Marketing and Advertising Campaign Development', 'Develop a comprehensive marketing and advertising campaign to generate excitement and anticipation for the new product.', 'COMPLETED', 'HIGH', '2024-08-11', '2024-09-05', 8, 7),
('Logistics Coordination for Product Distribution', 'Coordinate logistics for product distribution, including inventory management, shipping, and delivery schedules.', 'IN-PROGRESS', 'MEDIUM', '2024-09-06', NULL, 8, 7),

-- Tasks for Strategic Planning Initiative
('SWOT Analysis', 'Conduct a SWOT analysis to assess the company\'s strengths, weaknesses, opportunities, and threats.', 'COMPLETED', 'HIGH', '2024-09-05', '2024-09-15', 7, 8),
('Strategic Goals Definition', 'Define long-term strategic goals and objectives aligned with the company\'s mission and vision.', 'IN-PROGRESS', 'HIGH', '2024-09-16', NULL, 7, 8),
('Actionable Plans Development', 'Develop actionable plans with measurable milestones and timelines to achieve strategic objectives.', 'NOT-STARTED', 'MEDIUM', NULL, NULL, 7, 8),

-- Tasks for Mobile App Development
('User Persona and User Story Definition', 'Define user personas and user stories to guide the development process.', 'COMPLETED', 'HIGH', '2024-10-10', '2024-10-25', 9, 9),
('Wireframe and Prototype Design', 'Design wireframes and prototypes to visualise the app\'s interface and functionality.', 'COMPLETED', 'HIGH', '2024-10-26', '2024-11-25', 9, 9),
('Development and Testing', 'Develop and test the mobile app across multiple platforms, ensuring compatibility and usability.', 'COMPLETED', 'MEDIUM', '2024-11-26', '2024-12-25', 10, 9),

-- Tasks for Website Redesign
('Website Audit', 'Conduct a website audit to identify areas for improvement in design, navigation, and content.', 'IN-PROGRESS', 'HIGH', '2024-11-15', NULL, 9, 10),
('New Website Design Creation', 'Create a new website design that aligns with current branding guidelines and enhances user experience.', 'IN-PROGRESS', 'HIGH', '2024-12-01', NULL, 10, 10),
('Content Migration and Transition', 'Migrate content from the old website to the new design, ensuring smooth transition and minimal disruption for users.', 'NOT-STARTED', 'LOW', NULL, NULL, 10, 10);

-- Insert 30 Deadlines
INSERT INTO Deadline (DeadlineDate, TaskID)
VALUES

-- Deadlines for Project Infinity
('2024-03-15', 1),
('2024-03-15', 2),
('2024-03-15', 3),

-- Deadlines for Tech Solutions
('2024-07-20', 4),
('2024-07-20', 5),
('2024-07-20', 6),

-- Deadlines for Marketing Makeover
('2024-05-25', 7),
('2024-05-25', 8),
('2024-05-25', 9),

-- Deadlines for Operation Efficiency
('2024-06-30', 10),
('2024-06-30', 11),
('2024-06-30', 12),

-- Deadlines for Data Integration
('2024-07-15', 13),
('2024-07-15', 14),
('2024-07-15', 15),

-- Deadlines for Customer Experience Enhancement
('2024-08-10', 16),
('2024-08-10', 17),
('2024-08-10', 18),

-- Deadlines for Product Launch
('2024-09-15', 19),
('2024-09-15', 20),
('2024-09-15', 21),

-- Deadlines for Strategic Planning Initiative
('2024-10-20', 22),
('2024-10-20', 23),
('2024-10-20', 24),

-- Deadlines for Mobile App Development
('2024-12-25', 25),
('2024-12-25', 26),
('2024-12-25', 27),

-- Deadlines for Website Redesign
('2024-12-31', 28),
('2024-12-31', 29),
('2024-12-31', 30);

-- Insert 30 Progress
INSERT INTO Progress (PercentageComplete, TaskID)
VALUES

-- Progress for Project Infinity
(100, 1),
(100, 2),
(100, 3),

-- Progress for Tech Solutions
(100, 4),
(50, 5),
(60, 6),

-- Progress for Marketing Makeover
(100, 7),
(100, 8),
(100, 9),

-- Progress for Operation Efficiency
(100, 10),
(100, 11),
(75, 12),

-- Progress for Data Integration
(100, 13),
(65, 14),
(100, 15),

-- Progress for Customer Experience Enhancement
(0, 16),
(100, 17),
(55, 18),

-- Progress for Product Launch
(100, 19),
(100, 20),
(72, 21),

-- Progress for Strategic Planning Initiative
(100, 22),
(63, 23),
(0, 24),

-- Progress for Mobile App Development
(100, 25),
(100, 26),
(100, 27),

-- Progress for Website Redesign
(53, 28),
(42, 29),
(0, 30);

