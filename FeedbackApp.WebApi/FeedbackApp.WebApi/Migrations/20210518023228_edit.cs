using Microsoft.EntityFrameworkCore.Migrations;

namespace FeedbackApp.WebApi.Migrations
{
    public partial class edit : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "TypeName",
                table: "TypeFeedbacks",
                newName: "Name");

            migrationBuilder.RenameColumn(
                name: "TopicName",
                table: "Topics",
                newName: "Name");

            migrationBuilder.RenameColumn(
                name: "QuestionContent",
                table: "Questions",
                newName: "Content");

            migrationBuilder.RenameColumn(
                name: "ModuleName",
                table: "Modules",
                newName: "Name");

            migrationBuilder.RenameColumn(
                name: "ClassName",
                table: "Classes",
                newName: "Name");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameColumn(
                name: "Name",
                table: "TypeFeedbacks",
                newName: "TypeName");

            migrationBuilder.RenameColumn(
                name: "Name",
                table: "Topics",
                newName: "TopicName");

            migrationBuilder.RenameColumn(
                name: "Content",
                table: "Questions",
                newName: "QuestionContent");

            migrationBuilder.RenameColumn(
                name: "Name",
                table: "Modules",
                newName: "ModuleName");

            migrationBuilder.RenameColumn(
                name: "Name",
                table: "Classes",
                newName: "ClassName");
        }
    }
}
