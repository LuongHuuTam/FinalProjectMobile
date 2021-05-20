IF OBJECT_ID(N'[__EFMigrationsHistory]') IS NULL
BEGIN
    CREATE TABLE [__EFMigrationsHistory] (
        [MigrationId] nvarchar(150) NOT NULL,
        [ProductVersion] nvarchar(32) NOT NULL,
        CONSTRAINT [PK___EFMigrationsHistory] PRIMARY KEY ([MigrationId])
    );
END;
GO

BEGIN TRANSACTION;
GO

CREATE TABLE [Admins] (
    [UserName] nvarchar(450) NOT NULL,
    [Name] nvarchar(max) NULL,
    [Email] nvarchar(max) NULL,
    [Password] nvarchar(max) NULL,
    CONSTRAINT [PK_Admins] PRIMARY KEY ([UserName])
);
GO

CREATE TABLE [Classes] (
    [ClassID] int NOT NULL IDENTITY,
    [ClassName] nvarchar(max) NULL,
    [Capacity] int NOT NULL,
    [StartTime] datetime2 NOT NULL,
    [EndTime] datetime2 NOT NULL,
    [IsDeleted] bit NOT NULL DEFAULT CAST(0 AS bit),
    CONSTRAINT [PK_Classes] PRIMARY KEY ([ClassID])
);
GO

CREATE TABLE [Topics] (
    [TopicId] int NOT NULL IDENTITY,
    [TopicName] nvarchar(max) NULL,
    CONSTRAINT [PK_Topics] PRIMARY KEY ([TopicId])
);
GO

CREATE TABLE [Trainee_Assignments] (
    [RegistrationCode] nvarchar(450) NOT NULL,
    [TraineeId] nvarchar(450) NOT NULL,
    CONSTRAINT [PK_Trainee_Assignments] PRIMARY KEY ([RegistrationCode], [TraineeId])
);
GO

CREATE TABLE [Trainee_Comments] (
    [ClassID] int NOT NULL,
    [ModuleId] int NOT NULL,
    [TraineeID] nvarchar(450) NOT NULL,
    [Comment] nvarchar(max) NULL,
    CONSTRAINT [PK_Trainee_Comments] PRIMARY KEY ([ClassID], [ModuleId], [TraineeID])
);
GO

CREATE TABLE [Trainees] (
    [UserName] nvarchar(450) NOT NULL,
    [Name] nvarchar(max) NULL,
    [Email] nvarchar(max) NULL,
    [Password] nvarchar(max) NULL,
    [Phone] nvarchar(max) NULL,
    [Address] nvarchar(max) NULL,
    [IsActive] bit NOT NULL DEFAULT CAST(1 AS bit),
    [ActivationCode] nvarchar(max) NULL,
    [ResetPasswordCode] nvarchar(max) NULL,
    CONSTRAINT [PK_Trainees] PRIMARY KEY ([UserName])
);
GO

CREATE TABLE [Trainers] (
    [UserName] nvarchar(450) NOT NULL,
    [Name] nvarchar(max) NULL,
    [Email] nvarchar(max) NULL,
    [Password] nvarchar(max) NULL,
    [Phone] nvarchar(max) NULL,
    [Address] nvarchar(max) NULL,
    [IsActive] bit NOT NULL DEFAULT CAST(1 AS bit),
    [IsSkill] int NOT NULL,
    [ActivationCode] nvarchar(max) NULL,
    [ResetPasswordCode] nvarchar(max) NULL,
    [IsReceiveNotification] bit NOT NULL,
    CONSTRAINT [PK_Trainers] PRIMARY KEY ([UserName])
);
GO

CREATE TABLE [TypeFeedbacks] (
    [TypeId] int NOT NULL IDENTITY,
    [TypeName] nvarchar(max) NULL,
    [IsDeleted] bit NOT NULL DEFAULT CAST(0 AS bit),
    CONSTRAINT [PK_TypeFeedbacks] PRIMARY KEY ([TypeId])
);
GO

CREATE TABLE [Questions] (
    [QuestionID] int NOT NULL IDENTITY,
    [TopicID] int NOT NULL,
    [QuestionContent] nvarchar(max) NULL,
    [IsDeleted] bit NOT NULL DEFAULT CAST(0 AS bit),
    CONSTRAINT [PK_Questions] PRIMARY KEY ([QuestionID]),
    CONSTRAINT [FK_Questions_Topics_TopicID] FOREIGN KEY ([TopicID]) REFERENCES [Topics] ([TopicId]) ON DELETE CASCADE
);
GO

CREATE TABLE [Enrollments] (
    [ClassId] int NOT NULL,
    [TraineeId] nvarchar(450) NOT NULL,
    CONSTRAINT [PK_Enrollments] PRIMARY KEY ([ClassId], [TraineeId]),
    CONSTRAINT [FK_Enrollments_Classes_ClassId] FOREIGN KEY ([ClassId]) REFERENCES [Classes] ([ClassID]) ON DELETE CASCADE,
    CONSTRAINT [FK_Enrollments_Trainees_TraineeId] FOREIGN KEY ([TraineeId]) REFERENCES [Trainees] ([UserName]) ON DELETE CASCADE
);
GO

CREATE TABLE [Feedbacks] (
    [FeedbackID] int NOT NULL IDENTITY,
    [Title] nvarchar(max) NULL,
    [AdminID] nvarchar(450) NULL,
    [IsDeleted] bit NOT NULL DEFAULT CAST(0 AS bit),
    [TypeFeedbackID] int NOT NULL,
    CONSTRAINT [PK_Feedbacks] PRIMARY KEY ([FeedbackID]),
    CONSTRAINT [FK_Feedbacks_Admins_AdminID] FOREIGN KEY ([AdminID]) REFERENCES [Admins] ([UserName]) ON DELETE NO ACTION,
    CONSTRAINT [FK_Feedbacks_TypeFeedbacks_TypeFeedbackID] FOREIGN KEY ([TypeFeedbackID]) REFERENCES [TypeFeedbacks] ([TypeId]) ON DELETE CASCADE
);
GO

CREATE TABLE [Feedback_Questions] (
    [FeedbackId] int NOT NULL,
    [QuestionId] int NOT NULL,
    CONSTRAINT [PK_Feedback_Questions] PRIMARY KEY ([FeedbackId], [QuestionId]),
    CONSTRAINT [FK_Feedback_Questions_Feedbacks_FeedbackId] FOREIGN KEY ([FeedbackId]) REFERENCES [Feedbacks] ([FeedbackID]) ON DELETE CASCADE,
    CONSTRAINT [FK_Feedback_Questions_Questions_QuestionId] FOREIGN KEY ([QuestionId]) REFERENCES [Questions] ([QuestionID]) ON DELETE CASCADE
);
GO

CREATE TABLE [Modules] (
    [ModuleID] int NOT NULL IDENTITY,
    [AdminID] nvarchar(450) NULL,
    [ModuleName] nvarchar(max) NULL,
    [StartTime] datetime2 NOT NULL,
    [EndTime] datetime2 NOT NULL,
    [IsDeleted] bit NOT NULL DEFAULT CAST(0 AS bit),
    [FeedbackStartTime] datetime2 NOT NULL,
    [FeedbackEndTime] datetime2 NOT NULL,
    [FeedbackID] int NULL,
    CONSTRAINT [PK_Modules] PRIMARY KEY ([ModuleID]),
    CONSTRAINT [FK_Modules_Admins_AdminID] FOREIGN KEY ([AdminID]) REFERENCES [Admins] ([UserName]) ON DELETE NO ACTION,
    CONSTRAINT [FK_Modules_Feedbacks_FeedbackID] FOREIGN KEY ([FeedbackID]) REFERENCES [Feedbacks] ([FeedbackID]) ON DELETE NO ACTION
);
GO

CREATE TABLE [Answers] (
    [ClassId] int NOT NULL,
    [ModuleID] int NOT NULL,
    [TraineeID] nvarchar(450) NOT NULL,
    [QuestionID] int NOT NULL,
    [Value] int NOT NULL,
    CONSTRAINT [PK_Answers] PRIMARY KEY ([ClassId], [ModuleID], [QuestionID], [TraineeID]),
    CONSTRAINT [FK_Answers_Classes_ClassId] FOREIGN KEY ([ClassId]) REFERENCES [Classes] ([ClassID]) ON DELETE CASCADE,
    CONSTRAINT [FK_Answers_Modules_ModuleID] FOREIGN KEY ([ModuleID]) REFERENCES [Modules] ([ModuleID]) ON DELETE CASCADE,
    CONSTRAINT [FK_Answers_Questions_QuestionID] FOREIGN KEY ([QuestionID]) REFERENCES [Questions] ([QuestionID]) ON DELETE CASCADE,
    CONSTRAINT [FK_Answers_Trainees_TraineeID] FOREIGN KEY ([TraineeID]) REFERENCES [Trainees] ([UserName]) ON DELETE CASCADE
);
GO

CREATE TABLE [Assignments] (
    [ClassId] int NOT NULL,
    [ModuleID] int NOT NULL,
    [TrainerID] nvarchar(450) NOT NULL,
    [RegistrationCode] nvarchar(max) NULL,
    CONSTRAINT [PK_Assignments] PRIMARY KEY ([ClassId], [ModuleID], [TrainerID]),
    CONSTRAINT [FK_Assignments_Classes_ClassId] FOREIGN KEY ([ClassId]) REFERENCES [Classes] ([ClassID]) ON DELETE CASCADE,
    CONSTRAINT [FK_Assignments_Modules_ModuleID] FOREIGN KEY ([ModuleID]) REFERENCES [Modules] ([ModuleID]) ON DELETE CASCADE,
    CONSTRAINT [FK_Assignments_Trainers_TrainerID] FOREIGN KEY ([TrainerID]) REFERENCES [Trainers] ([UserName]) ON DELETE CASCADE
);
GO

IF EXISTS (SELECT * FROM [sys].[identity_columns] WHERE [name] IN (N'UserName', N'Email', N'Name', N'Password') AND [object_id] = OBJECT_ID(N'[Admins]'))
    SET IDENTITY_INSERT [Admins] ON;
INSERT INTO [Admins] ([UserName], [Email], [Name], [Password])
VALUES (N'admin', N'admin@gmail.com', N'admin', N'cc03e747a6afbbcbf8be7668acfebee5');
IF EXISTS (SELECT * FROM [sys].[identity_columns] WHERE [name] IN (N'UserName', N'Email', N'Name', N'Password') AND [object_id] = OBJECT_ID(N'[Admins]'))
    SET IDENTITY_INSERT [Admins] OFF;
GO

IF EXISTS (SELECT * FROM [sys].[identity_columns] WHERE [name] IN (N'TopicId', N'TopicName') AND [object_id] = OBJECT_ID(N'[Topics]'))
    SET IDENTITY_INSERT [Topics] ON;
INSERT INTO [Topics] ([TopicId], [TopicName])
VALUES (1, N'Training program & content (nội dung đào tạo)'),
(2, N'Trainer/Coach'),
(3, N'Cource organization (tổ chức khóa học)'),
(4, N'Other');
IF EXISTS (SELECT * FROM [sys].[identity_columns] WHERE [name] IN (N'TopicId', N'TopicName') AND [object_id] = OBJECT_ID(N'[Topics]'))
    SET IDENTITY_INSERT [Topics] OFF;
GO

IF EXISTS (SELECT * FROM [sys].[identity_columns] WHERE [name] IN (N'UserName', N'ActivationCode', N'Address', N'Email', N'IsActive', N'Name', N'Password', N'Phone', N'ResetPasswordCode') AND [object_id] = OBJECT_ID(N'[Trainees]'))
    SET IDENTITY_INSERT [Trainees] ON;
INSERT INTO [Trainees] ([UserName], [ActivationCode], [Address], [Email], [IsActive], [Name], [Password], [Phone], [ResetPasswordCode])
VALUES (N'trainee', NULL, NULL, N'admin@gmail.com', CAST(1 AS bit), N'admin', N'cc03e747a6afbbcbf8be7668acfebee5', NULL, NULL);
IF EXISTS (SELECT * FROM [sys].[identity_columns] WHERE [name] IN (N'UserName', N'ActivationCode', N'Address', N'Email', N'IsActive', N'Name', N'Password', N'Phone', N'ResetPasswordCode') AND [object_id] = OBJECT_ID(N'[Trainees]'))
    SET IDENTITY_INSERT [Trainees] OFF;
GO

IF EXISTS (SELECT * FROM [sys].[identity_columns] WHERE [name] IN (N'UserName', N'ActivationCode', N'Address', N'Email', N'IsActive', N'IsReceiveNotification', N'IsSkill', N'Name', N'Password', N'Phone', N'ResetPasswordCode') AND [object_id] = OBJECT_ID(N'[Trainers]'))
    SET IDENTITY_INSERT [Trainers] ON;
INSERT INTO [Trainers] ([UserName], [ActivationCode], [Address], [Email], [IsActive], [IsReceiveNotification], [IsSkill], [Name], [Password], [Phone], [ResetPasswordCode])
VALUES (N'trainer', NULL, NULL, N'admin@gmail.com', CAST(1 AS bit), CAST(0 AS bit), 0, N'admin', N'cc03e747a6afbbcbf8be7668acfebee5', NULL, NULL);
IF EXISTS (SELECT * FROM [sys].[identity_columns] WHERE [name] IN (N'UserName', N'ActivationCode', N'Address', N'Email', N'IsActive', N'IsReceiveNotification', N'IsSkill', N'Name', N'Password', N'Phone', N'ResetPasswordCode') AND [object_id] = OBJECT_ID(N'[Trainers]'))
    SET IDENTITY_INSERT [Trainers] OFF;
GO

IF EXISTS (SELECT * FROM [sys].[identity_columns] WHERE [name] IN (N'TypeId', N'TypeName') AND [object_id] = OBJECT_ID(N'[TypeFeedbacks]'))
    SET IDENTITY_INSERT [TypeFeedbacks] ON;
INSERT INTO [TypeFeedbacks] ([TypeId], [TypeName])
VALUES (1, N'InComplete'),
(2, N'Complete');
IF EXISTS (SELECT * FROM [sys].[identity_columns] WHERE [name] IN (N'TypeId', N'TypeName') AND [object_id] = OBJECT_ID(N'[TypeFeedbacks]'))
    SET IDENTITY_INSERT [TypeFeedbacks] OFF;
GO

CREATE INDEX [IX_Answers_ModuleID] ON [Answers] ([ModuleID]);
GO

CREATE INDEX [IX_Answers_QuestionID] ON [Answers] ([QuestionID]);
GO

CREATE INDEX [IX_Answers_TraineeID] ON [Answers] ([TraineeID]);
GO

CREATE INDEX [IX_Assignments_ModuleID] ON [Assignments] ([ModuleID]);
GO

CREATE INDEX [IX_Assignments_TrainerID] ON [Assignments] ([TrainerID]);
GO

CREATE INDEX [IX_Enrollments_TraineeId] ON [Enrollments] ([TraineeId]);
GO

CREATE INDEX [IX_Feedback_Questions_QuestionId] ON [Feedback_Questions] ([QuestionId]);
GO

CREATE INDEX [IX_Feedbacks_AdminID] ON [Feedbacks] ([AdminID]);
GO

CREATE INDEX [IX_Feedbacks_TypeFeedbackID] ON [Feedbacks] ([TypeFeedbackID]);
GO

CREATE INDEX [IX_Modules_AdminID] ON [Modules] ([AdminID]);
GO

CREATE INDEX [IX_Modules_FeedbackID] ON [Modules] ([FeedbackID]);
GO

CREATE INDEX [IX_Questions_TopicID] ON [Questions] ([TopicID]);
GO

INSERT INTO [__EFMigrationsHistory] ([MigrationId], [ProductVersion])
VALUES (N'20210517095829_initial', N'5.0.6');
GO

COMMIT;
GO

